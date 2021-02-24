package org.lxc.recruitment.controller

import org.lxc.recruitment.bean.ResponseBean
import org.lxc.recruitment.bean.SimpleRequestBean
import org.lxc.recruitment.constant.Constants
import org.lxc.recruitment.constant.Response
import org.lxc.recruitment.constant.UrlConstant
import org.lxc.recruitment.entity.*
import org.lxc.recruitment.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping("/admin")
@Controller
class AdministratorController {

    @Autowired
    lateinit var auditRepository: AuditRepository

    @Autowired
    lateinit var newsRepository: NewsRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userDeleteRepository: UserDeleteRepository

    @Autowired
    lateinit var administratorRepository: AdministratorRepository

    @Autowired
    lateinit var jobRepository: JobRepository

    /**
     * 获取用户列表
     */
    @RequestMapping(value = [UrlConstant.USER_LIST], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun listUser(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        val list = userRepository.findAll()
        list.forEachIndexed { index, userEntity ->
            // 过滤管理员
            if(userEntity.type == UserEntity.ADMIN){
                list.removeAt(index)
            }
            userEntity.name = UserController().getUserName(userEntity.id)
        }
        return Response.success(list)
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = [UrlConstant.USER_DELETE], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun deleteUser(@RequestBody userDeleteEntity: UserDeleteEntity): ResponseBean {
        userRepository.deleteById(userDeleteEntity.deletedId)
        return Response.success(userDeleteRepository.save(userDeleteEntity))
    }

    /**
     * 审核管理
     */
    @RequestMapping(value = [UrlConstant.AUDIT_JOB], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun auditJob(@RequestBody auditEntity: AuditEntity): ResponseBean {
        val job = jobRepository.findById(auditEntity.jobId).get()
        job.isAudit = 1
        // 状态置为已审核
        jobRepository.save(job)
        return Response.success(auditRepository.save(auditEntity))
    }

    /**
     * 新闻添加
     */
    @RequestMapping(value = [UrlConstant.NEWS_ADD], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun newsAdd(@RequestBody newsEntity: NewsEntity): ResponseBean {
        return Response.success(newsRepository.save(newsEntity))
    }

    /**
     * 新闻列表
     */
    @RequestMapping(value = [UrlConstant.NEWS_LIST], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun newsList(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        return Response.success(newsRepository.findAll())
    }

    /**
     * 新闻详情
     */
    @RequestMapping(value = [UrlConstant.NEWS_QUERY], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun newsQuery(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        return Response.success(newsRepository.findById(simpleRequestBean.id).get())
    }

    /**
     * 新闻删除
     */
    @RequestMapping(value = [UrlConstant.NEWS_DELETE], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun newsDelete(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        newsRepository.deleteById(simpleRequestBean.id)
        return Response.success("新闻删除成功")
    }

    /**
     * 管理员添加
     */
    @RequestMapping(value = [UrlConstant.ADMIN_ADD], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun adminAdd(@RequestBody admin: AdministratorEntity): ResponseBean {
        return Response.success(administratorRepository.save(admin))
    }

    /**
     * 管理员信息
     */
    @RequestMapping(value = [UrlConstant.ADMIN_QUERY], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun adminQuery(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        return Response.success(administratorRepository.findById(simpleRequestBean.id).get())
    }
}