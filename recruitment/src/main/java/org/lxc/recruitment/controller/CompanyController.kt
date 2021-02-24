package org.lxc.recruitment.controller

import org.lxc.recruitment.bean.ResponseBean
import org.lxc.recruitment.bean.SimpleRequestBean
import org.lxc.recruitment.constant.Constants
import org.lxc.recruitment.constant.Response
import org.lxc.recruitment.constant.UrlConstant
import org.lxc.recruitment.entity.*
import org.lxc.recruitment.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping("/company")
@Controller
class CompanyController {

    @Autowired
    lateinit var companyRepository: CompanyRepository

    @Autowired
    lateinit var resumeRepository: ResumeRepository

    @Autowired
    lateinit var jobRepository: JobRepository

    @Autowired
    lateinit var deliveryRepository: DeliveryRepository

    @Autowired
    lateinit var letterRepository: LetterRepository

    /**
     * 企业信息添加
     */
    @RequestMapping(value = [UrlConstant.COMPANY_INFO_ADD], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun addCompanyInfo(@RequestBody companyEntity: CompanyEntity): ResponseBean {
        return Response.success(companyRepository.save(companyEntity))
    }

    /**
     * 查询企业信息
     */
    @RequestMapping(value = [UrlConstant.COMPANY_INFO_QUERY], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun queryCompanyInfo(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        return Response.success(companyRepository.findById(simpleRequestBean.id).get())
    }

    /**
     * 简历搜索
     */
    @RequestMapping(value = [UrlConstant.RESUME_SEARCH], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun resumeSearch(@RequestBody resumeEntity: ResumeEntity): ResponseBean {
        val example = Example.of(resumeEntity)
        val resumes = resumeRepository.findAll(example)
        return if (resumes.size > 0) {
            Response.success(resumes)
        } else {
            Response.fail("暂无符合相关条件的简历")
        }
    }

    /**
     * 职位添加
     */
    @RequestMapping(value = [UrlConstant.JOB_ADD], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun jobAdd(@RequestBody jobEntity: JobEntity): ResponseBean {
        return Response.success(jobRepository.save(jobEntity))
    }

    /**
     * 职位删除
     */
    @RequestMapping(value = [UrlConstant.JOB_DELETE], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun jobDelete(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        jobRepository.deleteById(simpleRequestBean.id)
        return Response.success("删除成功")
    }

    /**
     * 职位列表
     */
    @RequestMapping(value = [UrlConstant.JOB_LIST], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun jobList(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        if (simpleRequestBean.id <= 0){
            // 过滤掉未审核的
            val list = jobRepository.findAll().filter {
                it.isAudit == 0
            }
            return Response.success(list)
        }
        return Response.success(jobRepository.findByCompanyId(simpleRequestBean.id))
    }

    /**
     * 职位查询
     */
    @RequestMapping(value = [UrlConstant.JOB_QUERY], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun jobQuery(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        return Response.success(jobRepository.findById(simpleRequestBean.id).get())
    }

    /**
     * 面试邀请
     */
    @RequestMapping(value = [UrlConstant.INTERVIEW], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun interview(@RequestBody deliveryEntity: DeliveryEntity): ResponseBean {
        val letter = LetterEntity()
        letter.receiver = resumeRepository.findById(deliveryEntity.resumeId).get().personalId
        letter.content = "${deliveryEntity.jobId}|${deliveryEntity.resumeId}"
        letter.sender = deliveryEntity.sender
        letter.senderName = deliveryEntity.senderName
        letter.title = "面试邀请"
        letter.type = LetterEntity.INTERVIEW
        letterRepository.save(letter)
        return Response.success(deliveryRepository.save(deliveryEntity))
    }
}