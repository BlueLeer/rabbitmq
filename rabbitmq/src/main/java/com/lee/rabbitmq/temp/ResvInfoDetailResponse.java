package com.lee.rabbitmq.temp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResvInfoDetailResponse implements Serializable{ 

	private static final long serialVersionUID = -5730176585751644586L;

	private String serviceOrderId;//服务订单id
	
	private String isForOthers;//是否为他人预约

	private String cusId;//客户id
	
	private String cusName;//客户姓名

	private String cusSex;//客户性别

	private String cusCardType;//客户证件类型

	private String cusCardNo;//客户证件号

	private String cusTel;//客户手机号

	private String cusEmail;//客户邮箱
	
	private String cusBirthday;//客户生日

	private String isGyna;//是否妇科检查

	private String sendEmialMethod;//邮件发送方式

	private String planName;//方案名

	private String city;//城市

	private String serviceName;//服务名

	private String serviceId;//服务id

	private String byselfpackage;//自费加项包

	private String freepackage;//免费加项包

	private String price;//总金额

	private String storeId;//门店id

	private String storeName;//门店名

	private String storeAddress;//门店地址

	private String resvDate;//预约日期

	private String itemTips;//项目提示

	
	private String resCusId;//预约人id
	
	private String resCusName;//预约人姓名

	private String resCusSex;//预约人性别

	private String resCusCardType;//预约人证件类型

	private String resCusCardNo;//预约人证件号

	private String resCusTel;//预约人手机号
	
	private String resCusBirthday;//预约人生日
	
	private String resCusEmail;//预约人邮箱
	

	private String departmentName;//科室名
	
	private String departmentId;//科室id

	private String resvId;//预约主键

	private String medicalDoctor;//医生

	private String medicalCountry;//国家

	private String medicalSituation;//就诊情况

	private String diseaseName;//疾病信息

	private String diseaseInfo;//病史情况

	private String familyDiseaseHistory;//家族史

	private String diseaseHistory;//既往史

	private String isHaveRelatedCheck;//是否做过相关检查

	private String serviceCusName;//服务人姓名

	private String serviceCusTel;//服务人手机号

	private String receivingCusName;//地接人姓名

	private String receivingCusTel;//地接人手机号
	
	private String maCusName;//陪诊人姓名
	
	private String maCusTel;//陪诊人手机号
	
	private String seekCusId;//就诊人id

	private String seekCusName;//就诊人姓名

	private String seekCusSex;//就诊人性别

	private String seekCardType;//就诊人证件类型

	private String seekCardNo;//就诊人证件号

	private String seekCusTel;//就诊人手机号

	private String seekCusBirthday;//就诊人生日

	private String seekCusEmail;//就诊人邮箱

	private String medicalDate;//就诊日期

	private String medicalTime;//就诊时间

	private String submitedDate;//預約提交时间

	private String reexaminationPurpose;//复诊目的

	private String supplier;//供应商
	
	private String medicalCardCode;//就诊卡
	
	private String socialCardCode;//医保卡
	
	private String relation;

	private String byselfpackageid;
	
	private String freepackageid;
	

	public String getByselfpackageid() {
		return byselfpackageid;
	}

	public void setByselfpackageid(String byselfpackageid) {
		this.byselfpackageid = byselfpackageid;
	}

	public String getFreepackageid() {
		return freepackageid;
	}

	public void setFreepackageid(String freepackageid) {
		this.freepackageid = freepackageid;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getSocialCardCode() {
		return socialCardCode;
	}

	public void setSocialCardCode(String socialCardCode) {
		this.socialCardCode = socialCardCode;
	}

	public String getMedicalCardCode() {
		return medicalCardCode;
	}

	public void setMedicalCardCode(String medicalCardCode) {
		this.medicalCardCode = medicalCardCode;
	}

	public String getMaCusName() {
		return maCusName;
	}

	public void setMaCusName(String maCusName) {
		this.maCusName = maCusName;
	}

	public String getMaCusTel() {
		return maCusTel;
	}

	public void setMaCusTel(String maCusTel) {
		this.maCusTel = maCusTel;
	}

	public String getIsForOthers() {
		return isForOthers;
	}

	public void setIsForOthers(String isForOthers) {
		this.isForOthers = isForOthers;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusSex() {
		return cusSex;
	}

	public void setCusSex(String cusSex) {
		this.cusSex = cusSex;
	}

	public String getCusCardType() {
		return cusCardType;
	}

	public void setCusCardType(String cusCardType) {
		this.cusCardType = cusCardType;
	}

	public String getCusCardNo() {
		return cusCardNo;
	}

	public void setCusCardNo(String cusCardNo) {
		this.cusCardNo = cusCardNo;
	}

	public String getCusTel() {
		return cusTel;
	}

	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getIsGyna() {
		return isGyna;
	}

	public void setIsGyna(String isGyna) {
		this.isGyna = isGyna;
	}

	public String getSendEmialMethod() {
		return sendEmialMethod;
	}

	public void setSendEmialMethod(String sendEmialMethod) {
		this.sendEmialMethod = sendEmialMethod;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getByselfpackage() {
		return byselfpackage;
	}

	public void setByselfpackage(String byselfpackage) {
		this.byselfpackage = byselfpackage;
	}

	public String getFreepackage() {
		return freepackage;
	}

	public void setFreepackage(String freepackage) {
		this.freepackage = freepackage;
	}

	public String getPrice() {
		return price;
	}

	public String getSeekCusId() {
		return seekCusId;
	}

	public void setSeekCusId(String seekCusId) {
		this.seekCusId = seekCusId;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getResvDate() {
		return resvDate;
	}

	public void setResvDate(String resvDate) {
		this.resvDate = resvDate;
	}

	public String getItemTips() {
		return itemTips;
	}

	public void setItemTips(String itemTips) {
		this.itemTips = itemTips;
	}

	public String getCusBirthday() {
		return cusBirthday;
	}

	public void setCusBirthday(String cusBirthday) {
		this.cusBirthday = cusBirthday;
	}

	public String getResCusName() {
		return resCusName;
	}

	public void setResCusName(String resCusName) {
		this.resCusName = resCusName;
	}

	public String getResCusSex() {
		return resCusSex;
	}

	public void setResCusSex(String resCusSex) {
		this.resCusSex = resCusSex;
	}

	public String getResCusCardType() {
		return resCusCardType;
	}

	public void setResCusCardType(String resCusCardType) {
		this.resCusCardType = resCusCardType;
	}

	public String getResCusCardNo() {
		return resCusCardNo;
	}

	public void setResCusCardNo(String resCusCardNo) {
		this.resCusCardNo = resCusCardNo;
	}

	public String getResCusTel() {
		return resCusTel;
	}

	public void setResCusTel(String resCusTel) {
		this.resCusTel = resCusTel;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getResvId() {
		return resvId;
	}

	public void setResvId(String resvId) {
		this.resvId = resvId;
	}

	public String getMedicalDoctor() {
		return medicalDoctor;
	}

	public void setMedicalDoctor(String medicalDoctor) {
		this.medicalDoctor = medicalDoctor;
	}

	public String getMedicalCountry() {
		return medicalCountry;
	}

	public void setMedicalCountry(String medicalCountry) {
		this.medicalCountry = medicalCountry;
	}


	public String getMedicalSituation() {
		return medicalSituation;
	}

	public void setMedicalSituation(String medicalSituation) {
		this.medicalSituation = medicalSituation;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getDiseaseInfo() {
		return diseaseInfo;
	}

	public void setDiseaseInfo(String diseaseInfo) {
		this.diseaseInfo = diseaseInfo;
	}

	public String getFamilyDiseaseHistory() {
		return familyDiseaseHistory;
	}

	public String getServiceOrderId() {
		return serviceOrderId;
	}

	public void setServiceOrderId(String serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public void setFamilyDiseaseHistory(String familyDiseaseHistory) {
		this.familyDiseaseHistory = familyDiseaseHistory;
	}

	public String getDiseaseHistory() {
		return diseaseHistory;
	}

	public void setDiseaseHistory(String diseaseHistory) {
		this.diseaseHistory = diseaseHistory;
	}

	public String getIsHaveRelatedCheck() {
		return isHaveRelatedCheck;
	}

	public void setIsHaveRelatedCheck(String isHaveRelatedCheck) {
		this.isHaveRelatedCheck = isHaveRelatedCheck;
	}

	public String getServiceCusName() {
		return serviceCusName;
	}

	public void setServiceCusName(String serviceCusName) {
		this.serviceCusName = serviceCusName;
	}

	public String getServiceCusTel() {
		return serviceCusTel;
	}

	public void setServiceCusTel(String serviceCusTel) {
		this.serviceCusTel = serviceCusTel;
	}

	public String getReceivingCusName() {
		return receivingCusName;
	}

	public void setReceivingCusName(String receivingCusName) {
		this.receivingCusName = receivingCusName;
	}

	public String getReceivingCusTel() {
		return receivingCusTel;
	}

	public void setReceivingCusTel(String receivingCusTel) {
		this.receivingCusTel = receivingCusTel;
	}

	public String getSeekCusName() {
		return seekCusName;
	}

	public void setSeekCusName(String seekCusName) {
		this.seekCusName = seekCusName;
	}

	public String getSeekCusSex() {
		return seekCusSex;
	}

	public void setSeekCusSex(String seekCusSex) {
		this.seekCusSex = seekCusSex;
	}

	public String getSeekCardType() {
		return seekCardType;
	}

	public void setSeekCardType(String seekCardType) {
		this.seekCardType = seekCardType;
	}

	public String getSeekCardNo() {
		return seekCardNo;
	}

	public void setSeekCardNo(String seekCardNo) {
		this.seekCardNo = seekCardNo;
	}

	public String getSeekCusTel() {
		return seekCusTel;
	}

	public void setSeekCusTel(String seekCusTel) {
		this.seekCusTel = seekCusTel;
	}

	public String getSeekCusBirthday() {
		return seekCusBirthday;
	}

	public void setSeekCusBirthday(String seekCusBirthday) {
		this.seekCusBirthday = seekCusBirthday;
	}

	public String getSeekCusEmail() {
		return seekCusEmail;
	}

	public void setSeekCusEmail(String seekCusEmail) {
		this.seekCusEmail = seekCusEmail;
	}

	public String getMedicalDate() {
		return medicalDate;
	}

	public void setMedicalDate(String medicalDate) {
		this.medicalDate = medicalDate;
	}

	public String getMedicalTime() {
		return medicalTime;
	}

	public void setMedicalTime(String medicalTime) {
		this.medicalTime = medicalTime;
	}

	public String getSubmitedDate() {
		return submitedDate;
	}

	public void setSubmitedDate(String submitedDate) {
		this.submitedDate = submitedDate;
	}

	public String getReexaminationPurpose() {
		return reexaminationPurpose;
	}

	public void setReexaminationPurpose(String reexaminationPurpose) {
		this.reexaminationPurpose = reexaminationPurpose;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getResCusId() {
		return resCusId;
	}

	public void setResCusId(String resCusId) {
		this.resCusId = resCusId;
	}

	public String getResCusBirthday() {
		return resCusBirthday;
	}

	public void setResCusBirthday(String resCusBirthday) {
		this.resCusBirthday = resCusBirthday;
	}

	public String getResCusEmail() {
		return resCusEmail;
	}

	public void setResCusEmail(String resCusEmail) {
		this.resCusEmail = resCusEmail;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
