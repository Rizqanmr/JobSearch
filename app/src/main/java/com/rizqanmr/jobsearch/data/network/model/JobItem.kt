package com.rizqanmr.jobsearch.data.network.model

import com.google.gson.annotations.SerializedName

data class JobItem(
    @field:SerializedName("company_logo")
    val companyLogo: String? = "",

    @field:SerializedName("how_to_apply")
    val howToApply: String? = "",

    @field:SerializedName("created_at")
    val createdAt: String? = "",

    @field:SerializedName("description")
    val description: String? = "",

    @field:SerializedName("company")
    val company: String? = "",

    @field:SerializedName("company_url")
    val companyUrl: String? = "",

    @field:SerializedName("location")
    val location: String? = "",

    @field:SerializedName("id")
    val id: String? = "",

    @field:SerializedName("type")
    val type: String? = "",

    @field:SerializedName("title")
    val title: String? = "",

    @field:SerializedName("url")
    val url: String? = ""
) {
    fun isFulltime(): String {
        return if (type == "Full Time") "Yes" else "No"
    }
}
