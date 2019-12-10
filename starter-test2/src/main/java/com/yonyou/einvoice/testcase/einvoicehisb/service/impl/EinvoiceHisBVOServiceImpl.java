package com.yonyou.einvoice.testcase.einvoicehisb.service.impl;

import com.yonyou.einvoice.common.metadata.service.AbstractMybatisService;
import com.yonyou.einvoice.testcase.einvoicehisb.entity.EinvoiceHisBVO;
import com.yonyou.einvoice.testcase.einvoicehisb.repository.EinvoiceHisBVOMapper;
import com.yonyou.einvoice.testcase.einvoicehisb.service.IEinvoiceHisBVOService;
import org.springframework.stereotype.Service;


/**
 * einvoice_his_b
 *
 * @author liuqiangm
 */

@Service
public class EinvoiceHisBVOServiceImpl
    extends AbstractMybatisService<EinvoiceHisBVO, EinvoiceHisBVOMapper>
    implements IEinvoiceHisBVOService {

}