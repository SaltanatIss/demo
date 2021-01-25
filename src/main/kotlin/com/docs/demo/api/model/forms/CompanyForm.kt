package com.docs.demo.api.model.forms

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class CompanyForm(@NotNull val name: String,
                       val country: String?,
                       val city: String?,
                       @Pattern(regexp = "^00(?:[0-9] ?){2,20}[0-9]$") val phone: String,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") val date: LocalDate,
                       val photo: MultipartFile)