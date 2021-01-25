package com.docs.demo.api.model.forms

import javax.validation.constraints.Size


data class CompanyListForm(@Size(min = 1) val list: List<CompanyForm>)