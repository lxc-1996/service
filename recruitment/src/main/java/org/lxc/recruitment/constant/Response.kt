package org.lxc.recruitment.constant

import org.lxc.recruitment.bean.ResponseBean
import org.lxc.recruitment.bean.SimpleResponseBody


object Response {

    fun success(data: Any? = null): ResponseBean {
        return createResponse("请求成功", data, ResponseCode.SUCCESS)
    }

    fun fail(title: String = "请求失败",data: Any? = null): ResponseBean {
        return createResponse(title, data, ResponseCode.ERROR)
    }

    private fun createResponse(title: String, data: Any?, success: Int): ResponseBean {
        val responseBean = ResponseBean()
        responseBean.code = success
        responseBean.title = title
        if (data != null) {
            responseBean.data = data
        } else {
            responseBean.data = SimpleResponseBody()
        }
        return responseBean
    }
}