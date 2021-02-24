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

@RequestMapping("/personal")
@Controller
class PersonalController {

    @Autowired
    lateinit var personRepository: PersonalRepository

    @Autowired
    lateinit var resumeRepository: ResumeRepository

    @Autowired
    lateinit var jobRepository: JobRepository

    @Autowired
    lateinit var collectionRepository: CollectionRepository

    @Autowired
    lateinit var deliveryRepository: DeliveryRepository

    @Autowired
    lateinit var letterRepository: LetterRepository

    /**
     * 搜索职位
     */
    @RequestMapping(value = [UrlConstant.SEARCH_JOB], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun getJobs(@RequestBody jobEntity: JobEntity): ResponseBean {
        val example = Example.of(jobEntity)
        val jobs = jobRepository.findAll(example)
        return if (jobs.size > 0) {
            Response.success(jobs)
        } else {
            Response.fail("暂无符合相关条件的职位")
        }
    }

    /**
     * 添加个人信息
     */
    @RequestMapping(value = [UrlConstant.ADD_PERSONAL_INFO], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun addPersonalInfo(@RequestBody personalEntity: PersonalEntity): ResponseBean {
        return Response.success(personRepository.save(personalEntity))
    }

    /**
     * 查询个人信息
     */
    @RequestMapping(value = [UrlConstant.QUERY_PERSONAL_INFO], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun queryPersonalInfo(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        val personal = personRepository.findById(simpleRequestBean.id).get()
        return Response.success(personal)
    }

    /**
     * 职位收藏
     */
    @RequestMapping(value = [UrlConstant.COLLECTION_JOB], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun collectionJob(@RequestBody collectionEntity: CollectionEntity): CollectionEntity {
        collectionEntity.jobName = jobRepository.findById(collectionEntity.id).get().name
        return collectionRepository.save(collectionEntity)
    }

    /**
     * 取消收藏
     */
    @RequestMapping(value = [UrlConstant.CANCEL_COLLECTION_JOB], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun cancelCollectionJob(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        collectionRepository.deleteById(simpleRequestBean.id)
        return Response.success("取消收藏成功")
    }

    /**
     * 收藏列表
     */
    @RequestMapping(value = [UrlConstant.COLLECTION_LIST], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun collectionList(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        return Response.success(collectionRepository.findByUserId(simpleRequestBean.id))
    }

    /**
     * 删除简历
     */
    @RequestMapping(value = [UrlConstant.RESUME_DELETE], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun deleteResume(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        resumeRepository.deleteById(simpleRequestBean.id)
        return Response.success("删除成功")
    }

    /**
     * 简历添加
     */
    @RequestMapping(value = [UrlConstant.RESUME_ADD], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun addResume(@RequestBody resumeEntity: ResumeEntity): ResponseBean {
        return Response.success(resumeRepository.save(resumeEntity))
    }

    /**
     * 简历列表
     */
    @RequestMapping(value = [UrlConstant.RESUME_LIST], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun resumeList(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        val list =  resumeRepository.findByPersonalId(simpleRequestBean.id)
        return Response.success(list)
    }

    /**
     * 简历查看
     */
    @RequestMapping(value = [UrlConstant.RESUME_QUERY], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun queryResume(@RequestBody simpleRequestBean: SimpleRequestBean): ResponseBean {
        return Response.success(resumeRepository.findById(simpleRequestBean.id).get())
    }


    /**
     * 简历投递
     */
    @RequestMapping(value = [UrlConstant.RESUME_DELIVERY], produces = [Constants.PARAM_JSON])
    @ResponseBody
    fun deliveryResume(@RequestBody deliveryEntity: DeliveryEntity): ResponseBean {
        val letter = LetterEntity()
        letter.receiver = jobRepository.findById(deliveryEntity.jobId).get().companyId
        letter.content = "${deliveryEntity.jobId}|${deliveryEntity.resumeId}"
        letter.sender = deliveryEntity.sender
        letter.senderName = deliveryEntity.senderName
        letter.title = "简历投递"
        letter.type = LetterEntity.DELIVERY
        letterRepository.save(letter)
        return Response.success(deliveryRepository.save(deliveryEntity))
    }
}