package com.bean;

public class CityBean {

	private int id;
	private String name;	//������
	private String postcode;	//��������
	private String createDate;	//����ʱ��
	private int provinceId;	//ʡid
	private ProvinceBean provinceBean;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public ProvinceBean getProvinceBean() {
		return provinceBean;
	}
	public void setProvinceBean(ProvinceBean provinceBean) {
		this.provinceBean = provinceBean;
	}
	
	
}
