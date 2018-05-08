package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewPageHeader;

public interface BaseMvcService
{

//    public void addViewFieldSpec(List<ViewFieldSpec> viewFieldSpecList, Boolean hiddenFlag, Boolean disable,
//            Boolean required, String dataType, String inputType, String label, String name, String value);
//
//    public void addViewFieldSpec(List<ViewFieldSpec> viewFieldSpecList, String inputType, String label, String name,
//            String value);

    public void addViewPageHeader(List<ViewPageHeader> pageHeaders, String label, String name, Integer seq, Boolean hid);

}
