package com.dpu.bean;

import java.io.Serializable;
import java.util.List;

public class PurchaseOrderModel implements Serializable{

	 
	private static final long serialVersionUID = 1L;

	private Long id;

	private String title;
	
	private String vendorName;
	private Long vendorId;
	private List<VendorModel> vendorList;
	
	private String categoryName;
	private Long categoryId;
	private List<CategoryReq> categoryList;
	
	private String unitTypeName;
	private Long unitTypeId;
	private List<TypeResponse> unitTypeList;
	
	private String issueName;
	private Long issueId;
	private List<IssueModel> issueList;
	
	private String statusName;
	private Long statusId;
	private List<TypeResponse> statusList;
	
	private String invoiceNo;
	
	private String message;
	
	private List<Long> issueIds;
	
	private Long PoNo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public List<TypeResponse> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<TypeResponse> statusList) {
		this.statusList = statusList;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public List<VendorModel> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<VendorModel> vendorList) {
		this.vendorList = vendorList;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public List<IssueModel> getIssueList() {
		return issueList;
	}

	public void setIssueList(List<IssueModel> issueList) {
		this.issueList = issueList;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<CategoryReq> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryReq> categoryList) {
		this.categoryList = categoryList;
	}

	public String getUnitTypeName() {
		return unitTypeName;
	}

	public void setUnitTypeName(String unitTypeName) {
		this.unitTypeName = unitTypeName;
	}

	public Long getUnitTypeId() {
		return unitTypeId;
	}

	public void setUnitTypeId(Long unitTypeId) {
		this.unitTypeId = unitTypeId;
	}

	public List<TypeResponse> getUnitTypeList() {
		return unitTypeList;
	}

	public void setUnitTypeList(List<TypeResponse> unitTypeList) {
		this.unitTypeList = unitTypeList;
	}

	public List<Long> getIssueIds() {
		return issueIds;
	}

	public void setIssueIds(List<Long> issueIds) {
		this.issueIds = issueIds;
	}

	public Long getPoNo() {
		return PoNo;
	}

	public void setPoNo(Long poNo) {
		PoNo = poNo;
	}

	/*public List<Long> getIssueIds() {
		return issueIds;
	}

	public void setIssueIds(List<Long> issueIds) {
		this.issueIds = issueIds;
	}*/
}
