package com.docs.demo.api.controller

import com.docs.demo.api.model.dtos.CompanyDto
import com.docs.demo.api.model.forms.CompanyForm
import com.docs.demo.api.model.forms.CompanyListForm
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class CompanyController{

    @GetMapping("/company/{id}")
    fun postCompany(@PathVariable id: String) :CompanyDto{
        return CompanyDto("test", "Germany", "Berlin", TimeZone.getDefault())
    }

    @PostMapping("/company")
    fun postCompany(@ModelAttribute companyForm: CompanyForm) :CompanyDto{
        return CompanyDto(companyForm.name, companyForm.country, companyForm.city, TimeZone.getDefault())
    }

    @PostMapping("/company-list")
    fun postCompanyList(@RequestBody list: CompanyListForm): List<CompanyDto> {
        return listOf()
    }
}