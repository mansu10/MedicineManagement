package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Product;
import com.medicine.util.MedicineRuntimeException;

public class ProductDAO {
	
	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public List<Product> findAllProducts(String productName, String herbsType){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Product> products = new ArrayList<Product>();
		String sql = "select id,product_code,ordinary_name,product_name,specifications,unit,first_level,"
				+ "second_level,manufactor,control_code,model,max_conversion_ratio,herbs_type,unity_product_code,pinyin_code,formulations,"
				+ "approval_number,price,max_unit,max_length,max_width,max_heigth,max_number,storage_coditions,validity,"
				+ "storage_min,storage_max,locator_code,standard1,standard2,standard3,standard4,standard5,"
				+ "third_level,management_classification,specification_sets,case_type,case_number,single_box_volume,set_volume,set_price,set_weight,manufactor_code,"
				+ "min_unit,min_converstion_ratio,min_price,min_weight,min_volume,min_material "
				+ "from tbl_product "
				+ "where 1=1 ";
		if (productName != null && !"".equals(productName)) {
			sql += " and product_name = ?";
		}
		if (herbsType != null && !"".equals(herbsType)) {
			sql += " and herbs_type = ?";
		}
		try {  
			stmt = this.getConn().prepareStatement(sql);
			int length = 1;
			if (productName != null && !"".equals(productName)) {
				stmt.setString(length, productName);
				length++;
			}
			if (herbsType != null && !"".equals(herbsType)) {
				stmt.setString(length, herbsType);
				length++;
			}
			rs = stmt.executeQuery();
            while (rs.next()) {  
				Product product = new Product();
            	product.setId(rs.getInt("id"));
            	product.setProductCode(rs.getInt("product_code"));
            	product.setOrdinaryName(rs.getString("ordinary_name"));
            	product.setProductName(rs.getString("product_name"));
            	product.setSpecifications(rs.getString("specifications"));
            	product.setUnit(rs.getString("unit"));
            	product.setFirstLevel(rs.getString("first_level"));
            	product.setSecondLevel(rs.getString("second_level"));
            	product.setManufactor(rs.getString("manufactor"));
            	product.setControlCode(rs.getString("control_code"));
            	product.setModel(rs.getString("model"));
            	product.setHerbsType(rs.getString("herbs_type"));
            	product.setUnityProductCode(rs.getString("unity_product_code"));
            	product.setPinyinCode(rs.getString("pinyin_code"));
            	product.setFormulations(rs.getString("formulations"));
            	product.setApprovalNumber(rs.getString("approval_number"));
            	product.setPrice(rs.getFloat("price"));
            	product.setMaxConversionRatio(rs.getFloat("max_conversion_ratio"));
            	product.setMaxUnit(rs.getString("max_unit"));
            	product.setMaxLength(rs.getInt("max_length"));
            	product.setMaxWidth(rs.getInt("max_width"));
            	product.setMaxHeigth(rs.getInt("max_heigth"));
            	product.setMaxNumber(rs.getInt("max_number"));
            	product.setStorageCoditions(rs.getString("storage_coditions"));
            	product.setValidity(rs.getInt("validity"));
            	product.setStorageMin(rs.getInt("storage_min"));
            	product.setStorageMax(rs.getInt("storage_max"));
            	product.setLocatorCode(rs.getString("locator_code"));
            	product.setStandard1(rs.getString("standard1"));
            	product.setStandard2(rs.getString("standard2"));
            	product.setStandard3(rs.getString("standard3"));
            	product.setStandard4(rs.getString("standard4"));
            	product.setStandard5(rs.getString("standard5"));
        		product.setThirdLevel(rs.getString("third_level"));
            	product.setManagementClassification(rs.getString("management_classification"));
            	product.setSpecificationSets(rs.getString("specification_sets"));
            	product.setCaseType(rs.getString("case_type"));
            	product.setCaseNumber(rs.getInt("case_number"));
            	product.setSingleBoxVolume(rs.getInt("single_box_volume"));
            	product.setSetVolume(rs.getInt("set_volume"));
            	product.setSetPrice(rs.getFloat("set_price"));
            	product.setSetWeight(rs.getFloat("set_weight"));
            	product.setManufactorCode(rs.getString("manufactor_code"));
            	product.setMinMaterial(rs.getString("min_material"));
            	product.setMinUnit(rs.getString("min_unit"));
            	product.setMinConversionRatio(rs.getFloat("min_converstion_ratio"));
            	product.setMinPrice(rs.getFloat("min_price"));
            	product.setMinWeight(rs.getInt("min_weight"));
            	product.setMinVolume(rs.getFloat("min_volume"));
                products.add(product);
            }
        } catch (SQLException ex) {
			throw new MedicineRuntimeException(ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		return products;
	}
	
	public Product findProductByCode(int productCode){
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Product product = new Product();
		String sql = "select id,product_code,ordinary_name,product_name,specifications,unit,min_price,"
				+ "min_number,min_volume,min_weight,price,volume,weight,first_level,"
				+ "second_level,manufactor,approval_number,validity,unit_dose,average_dose,"
				+ "average_number,max_number,create_time,model,min_stock,max_stock,supplier,locator_code "
				+ "from tbl_product "
				+ "where product_code = ?";
		try {  
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productCode);
			rs = stmt.executeQuery();
            while (rs.next()) {  
            	product.setId(rs.getInt("id"));
            	product.setProductCode(rs.getInt("product_code"));
            	product.setOrdinaryName(rs.getString("ordinary_name"));
            	product.setProductName(rs.getString("product_name"));
            	product.setSpecifications(rs.getString("specifications"));
            	product.setUnit(rs.getString("unit"));
            	product.setMinPrice(rs.getFloat("min_price"));
            	product.setMinNumber(rs.getInt("min_number"));
            	product.setMinVolume(rs.getFloat("min_volume"));
            	product.setMinWeight(rs.getInt("min_weight"));
            	product.setPrice(rs.getFloat("price"));
            	product.setVolume(rs.getFloat("volume"));
            	product.setWeight(rs.getFloat("weight"));
            	product.setFirstLevel(rs.getString("first_level"));
            	product.setSecondLevel(rs.getString("second_level"));
            	product.setManufactor(rs.getString("manufactor"));
            	product.setApprovalNumber(rs.getString("approval_number"));
            	product.setValidity(rs.getInt("validity"));
            	product.setUnitDose(rs.getString("unit_dose"));
            	product.setAverageDose(rs.getFloat("average_dose"));
            	product.setAverageNumber(rs.getInt("average_number"));
            	product.setMaxNumber(rs.getInt("max_number"));
            	product.setCreateTime(rs.getTimestamp("create_time"));
            	product.setModel(rs.getString("model"));
            	product.setMinStock(rs.getInt("min_stock"));
            	product.setMaxStock(rs.getInt("max_stock"));
            	product.setSupplier(rs.getString("supplier"));
            	product.setLocatorCode(rs.getString("locator_code"));
            }
        } catch (SQLException ex) {
			throw new MedicineRuntimeException(ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		return product;
	}
	
	public Product findProductByCodeOrName(String productCodeOrName){
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Product product = new Product();
		String sql = "select id,product_code,ordinary_name,product_name,specifications,unit,min_price,"
				+ "min_number,min_volume,min_weight,price,volume,weight,first_level,"
				+ "second_level,manufactor,approval_number,validity,unit_dose,average_dose,"
				+ "average_number,max_number,create_time,model,min_stock,max_stock "
				+ "from tbl_product "
				+ "where product_code = ? or ordinary_name = ?";
		try {  
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, productCodeOrName);
			stmt.setString(2, productCodeOrName);
			rs = stmt.executeQuery();
            while (rs.next()) {  
            	product.setId(rs.getInt("id"));
            	product.setProductCode(rs.getInt("product_code"));
            	product.setOrdinaryName(rs.getString("ordinary_name"));
            	product.setProductName(rs.getString("product_name"));
            	product.setSpecifications(rs.getString("specifications"));
            	product.setUnit(rs.getString("unit"));
            	product.setMinPrice(rs.getFloat("min_price"));
            	product.setMinNumber(rs.getInt("min_number"));
            	product.setMinVolume(rs.getFloat("min_volume"));
            	product.setMinWeight(rs.getInt("min_weight"));
            	product.setPrice(rs.getFloat("price"));
            	product.setVolume(rs.getFloat("volume"));
            	product.setWeight(rs.getFloat("weight"));
            	product.setFirstLevel(rs.getString("first_level"));
            	product.setSecondLevel(rs.getString("second_level"));
            	product.setManufactor(rs.getString("manufactor"));
            	product.setApprovalNumber(rs.getString("approval_number"));
            	product.setValidity(rs.getInt("validity"));
            	product.setUnitDose(rs.getString("unit_dose"));
            	product.setAverageDose(rs.getFloat("average_dose"));
            	product.setAverageNumber(rs.getInt("average_number"));
            	product.setMaxNumber(rs.getInt("max_number"));
            	product.setCreateTime(rs.getTimestamp("create_time"));
            	product.setModel(rs.getString("model"));
            	product.setMinStock(rs.getInt("min_stock"));
            	product.setMaxStock(rs.getInt("max_stock"));
            }
        } catch (SQLException ex) {
			throw new MedicineRuntimeException(ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		return product;
	}
	
	public int addProduct(Product product) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_product (product_code,ordinary_name,product_name,specifications,unit,first_level,"
				+ "second_level,manufactor,control_code,model,max_conversion_ratio,herbs_type,unity_product_code,pinyin_code,formulations,"
				+ "approval_number,price,max_unit,max_length,max_width,max_heigth,max_number,storage_coditions,validity,"
				+ "storage_min,storage_max,locator_code,standard1,unit_dose,average_dose,average_number,material,weight,standard2,standard3,standard4,standard5,"
				+ "third_level,management_classification,specification_sets,case_type,case_number,single_box_volume,set_volume,set_price,set_weight,manufactor_code,"
				+ "min_unit,min_converstion_ratio,min_price,min_weight,min_volume,min_material) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, product.getProductCode());
			stmt.setString(2, product.getOrdinaryName());
			stmt.setString(3, product.getProductName());
			stmt.setString(4, product.getSpecifications());
			stmt.setString(5, product.getUnit());
			stmt.setString(6, product.getFirstLevel());
			stmt.setString(7, product.getSecondLevel());
			stmt.setString(8, product.getManufactor());
			stmt.setString(9, product.getControlCode());
			stmt.setString(10, product.getModel());
			stmt.setFloat(11, product.getMaxConversionRatio());
			stmt.setString(12, product.getHerbsType());
			stmt.setString(13, product.getUnityProductCode());
			stmt.setString(14, product.getPinyinCode());
			stmt.setString(15, product.getFormulations());
			stmt.setString(16, product.getApprovalNumber());
			stmt.setFloat(17, product.getPrice());
			stmt.setString(18, product.getMaxUnit());
			stmt.setInt(19, product.getMaxLength());
			stmt.setInt(20, product.getMaxWidth());
			stmt.setInt(21, product.getMaxHeigth());
			stmt.setInt(22, product.getMaxNumber());
			stmt.setString(23, product.getStorageCoditions());
			stmt.setInt(24, product.getValidity());
			stmt.setInt(25, product.getStorageMin());
			stmt.setInt(26, product.getStorageMax());
			stmt.setString(27, product.getLocatorCode());
			stmt.setString(28, product.getStandard1());
			stmt.setString(29, product.getUnitDose());
			stmt.setFloat(30, product.getAverageDose());
			stmt.setInt(31, product.getAverageNumber());
			stmt.setString(32, product.getMaterial());
			stmt.setFloat(33, product.getWeight());
			stmt.setString(34, product.getStandard2());
			stmt.setString(35, product.getStandard3());
			stmt.setString(36, product.getStandard4());
			stmt.setString(37, product.getStandard5());
			stmt.setString(38, product.getThirdLevel());
			stmt.setString(39, product.getManagementClassification());
			stmt.setString(40, product.getSpecificationSets());
			stmt.setString(41, product.getCaseType());
			stmt.setInt(42, product.getCaseNumber());
			stmt.setInt(43, product.getSingleBoxVolume());
			stmt.setInt(44, product.getSetVolume());
			stmt.setFloat(45, product.getSetPrice());
			stmt.setFloat(46, product.getSetWeight());
			stmt.setString(47, product.getManufactorCode());
			stmt.setString(48, product.getMinUnit());
			stmt.setFloat(49, product.getMinConversionRatio());
			stmt.setFloat(50, product.getMinPrice());
			stmt.setInt(51, product.getMinWeight());
			stmt.setFloat(52, product.getMinVolume());
			stmt.setString(53, product.getMinMaterial());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		return count;
	}
	
	public int updateProduct(Product product) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "update tbl_product set product_code=?,ordinary_name=?,product_name=?,specifications=?,unit=?,first_level=?,"
				+ "second_level=?,manufactor=?,control_code=?,model=?,max_conversion_ratio=?,min_material=?,unity_product_code=?,pinyin_code=?,formulations=?,"
				+ "approval_number=?,price=?,max_unit=?,max_length=?,max_width=?,max_heigth=?,max_number=?,storage_coditions=?,validity=?,"
				+ "storage_min=?,storage_max=?,locator_code=?,standard1=?,unit_dose=?,average_dose=?,average_number=?,material=?,weight=?,standard2=?,standard3=?,"
				+ "standard4=?,standard5=?,"
				+ "third_level=?,management_classification=?,specification_sets=?,case_type=?,case_number=?,single_box_volume=?,set_volume=?,"
				+ "set_price=?,set_weight=?,manufactor_code=?,"
				+ "min_unit=?,min_converstion_ratio=?,min_price=?,min_weight=?,min_volume=? "
				+ "where id = ? ";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, product.getProductCode());
			stmt.setString(2, product.getOrdinaryName());
			stmt.setString(3, product.getProductName());
			stmt.setString(4, product.getSpecifications());
			stmt.setString(5, product.getUnit());
			stmt.setString(6, product.getFirstLevel());
			stmt.setString(7, product.getSecondLevel());
			stmt.setString(8, product.getManufactor());
			stmt.setString(9, product.getControlCode());
			stmt.setString(10, product.getModel());
			stmt.setFloat(11, product.getMaxConversionRatio());
			stmt.setString(12, product.getMinMaterial());
			stmt.setString(13, product.getUnityProductCode());
			stmt.setString(14, product.getPinyinCode());
			stmt.setString(15, product.getFormulations());
			stmt.setString(16, product.getApprovalNumber());
			stmt.setFloat(17, product.getPrice());
			stmt.setString(18, product.getMaxUnit());
			stmt.setInt(19, product.getMaxLength());
			stmt.setInt(20, product.getMaxWidth());
			stmt.setInt(21, product.getMaxHeigth());
			stmt.setInt(22, product.getMaxNumber());
			stmt.setString(23, product.getStorageCoditions());
			stmt.setInt(24, product.getValidity());
			stmt.setInt(25, product.getStorageMin());
			stmt.setInt(26, product.getStorageMax());
			stmt.setString(27, product.getLocatorCode());
			stmt.setString(28, product.getStandard1());
			stmt.setString(29, product.getUnitDose());
			stmt.setFloat(30, product.getAverageDose());
			stmt.setInt(31, product.getAverageNumber());
			stmt.setString(32, product.getMaterial());
			stmt.setFloat(33, product.getWeight());
			stmt.setString(34, product.getStandard2());
			stmt.setString(35, product.getStandard3());
			stmt.setString(36, product.getStandard4());
			stmt.setString(37, product.getStandard5());
			stmt.setString(38, product.getThirdLevel());
			stmt.setString(39, product.getManagementClassification());
			stmt.setString(40, product.getSpecificationSets());
			stmt.setString(41, product.getCaseType());
			stmt.setInt(42, product.getCaseNumber());
			stmt.setInt(43, product.getSingleBoxVolume());
			stmt.setInt(44, product.getSetVolume());
			stmt.setFloat(45, product.getSetPrice());
			stmt.setFloat(46, product.getSetWeight());
			stmt.setString(47, product.getManufactorCode());
			stmt.setString(48, product.getMinUnit());
			stmt.setFloat(49, product.getMinConversionRatio());
			stmt.setFloat(50, product.getMinPrice());
			stmt.setInt(51, product.getMinWeight());
			stmt.setFloat(52, product.getMinVolume());
			
			stmt.setInt(53, product.getId());
			stmt.executeUpdate();
			count++;
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		return count;
	}
	
	public int deleteProduct(int id) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "delete from tbl_product where id = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setLong(1, id);
			count = stmt.executeUpdate();
		} catch(SQLException ex){
			throw new MedicineRuntimeException(ex);
		}finally{
			if(stmt!=null){
				try{stmt.close();}catch(SQLException ex){}
			}
		}
		return count;
	}
}
