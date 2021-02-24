package org.lxc.recruitment.controller

import org.lxc.recruitment.constant.Constants
import org.lxc.recruitment.constant.Response
import org.lxc.recruitment.constant.UrlConstant
import org.lxc.recruitment.bean.ResponseBean
import org.lxc.recruitment.bean.SimpleRequestBean
import org.lxc.recruitment.entity.*
import org.lxc.recruitment.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping("/user")
@Controller
class UserController {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var letterRepository: LetterRepository

    @Autowired
    lateinit var adminRepository: AdministratorRepository
    @Autowired
    lateinit var personalRepository: PersonalRepository
    @Autowired
    lateinit var companyRepository: CompanyRepository
    @Autowired
    lateinit var dictRepository: DictRepository
    /**
     * 注册账户
     */
    @RequestMapping(value = [UrlConstant.REGISTER], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun register(@RequestBody user: UserEntity): ResponseBean {
        val dbUser = getUser(user)
        return if(dbUser != null){
            Response.fail("账户名已存在!")
        } else {
            val user = userRepository.save(user)
            when (user.type) {
                UserEntity.ADMIN -> {
                    val admin = AdministratorEntity()
                    admin.id = user.id
                    admin.name = user.account
                    admin.number = ""
                    admin.phone = ""
                    admin.sex = "未知"
                    adminRepository.save(admin)
                }
                UserEntity.COMPANY ->{
                    val company = CompanyEntity()
                    company.id = user.id
                    company.name = user.account
                    company.license = ""
                    companyRepository.save(company)
                }
                UserEntity.PERSONAL ->{
                    val personal =PersonalEntity()
                    personal.id = user.id
                    personal.name = user.account
                    personal.sex = "未知"
                    personalRepository.save(personal)
                }
            }
            Response.success(user)
        }
    }

    /**
     * 登录账户
     */
    @RequestMapping(value = [UrlConstant.LOGIN], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun login(@RequestBody user: UserEntity): ResponseBean {
        val dbUser = getUser(user)
        return if(dbUser != null){
            if (dbUser.password == user.password) {
                Response.success(dbUser)
            } else {
                Response.fail("密码错误!")
            }
        } else {
            Response.fail("用户不存在!")
        }
    }

    /**
     * 找回密码
     */
    @RequestMapping(value = [UrlConstant.CHANGE_PASSWORD], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun findPassword(@RequestBody user: UserEntity): ResponseBean {
        val dbUser = getUser(user)
        return if(dbUser != null){
            if (dbUser.answer == user.answer) {
                dbUser.password = user.password
                userRepository.save(dbUser)
                Response.success(dbUser)
            } else {
                Response.fail("密保答案错误!")
            }
        } else {
            Response.fail("用户不存在!")
        }
    }

    /**
     * 获取用户信息（用于找回密码）
     */
    @RequestMapping(value = [UrlConstant.USER_INFO], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun getUserInfo(@RequestBody user: UserEntity): ResponseBean {
        val dbUser = getUser(user)
        return if(dbUser != null){
            // 隐藏密码
            dbUser.password = "***"
            dbUser.answer = "***"
            Response.success(dbUser)
        } else {
            Response.fail("用户不存在!")
        }
    }

    /**
     * 发送站内信
     */
    @RequestMapping(value = [UrlConstant.LETTER], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun sendLetter(@RequestBody letter: LetterEntity): ResponseBean {
        return Response.success(letterRepository.save(letter))
    }

    /**
     * 获取用户收到的站内信
     */
    @RequestMapping(value = [UrlConstant.USER_RECEIVER], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun getReceiver(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        val list = letterRepository.findByReceiver(simpleRequestBean.id)
        list.forEach {
           it.senderName = getUserName(it.sender)
        }
        return Response.success(list)
    }

    /**
     * 获取数据库中用户
     */
    private fun getUser(user: UserEntity): UserEntity? {
        val list = userRepository.findByAccount(user.account)
        return if(list.size >0 ){
            list[0]
        } else {
            null
        }
    }

    /**
     * 获取数据库中用户姓名
     */
    fun getUserName(id:Long): String {
        val user = userRepository.findById(id).get()
        when(user.type){
            UserEntity.ADMIN ->{
                return adminRepository.findById(id).get().name
            }
            UserEntity.COMPANY ->{
                return companyRepository.findById(id).get().name
            }
            UserEntity.PERSONAL ->{
                return personalRepository.findById(id).get().name
            }
        }
        return ""
    }

    /**
     * 获取数据库
     */
    @RequestMapping(value = [UrlConstant.DICT], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun getDict(): ResponseBean {
        return Response.success(dictRepository.findAll())
    }
}