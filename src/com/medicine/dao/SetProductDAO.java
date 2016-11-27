package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.SetProduct;
import com.medicine.util.MedicineRuntimeException;

public class SetProductDAO {

	private Connection getConn() throws SQLException {
		return DBHelper.getConn();
	}

	public int addSetProduct(SetProduct setProduct) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_set_product (set_product_code,sub_product_code,sub_product_number) values (?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, setProduct.getSetProductCode());
			stmt.setString(2, setProduct.getSubProductCode());
			stmt.setInt(3, setProduct.getSubProductNumber());
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

	public List<SetProduct> findAllSetProducts(String productCode) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<SetProduct> setProducts = new ArrayList<SetProduct>();
		String sql = "select * from tbl_set_product where set_product_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, productCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				SetProduct setProduct = new SetProduct();
				setProduct.setSetProductCode(rs.getString("set_product_code"));
				setProduct.setSubProductCode(rs.getString("sub_product_code"));
				setProduct.setSubProductNumber(rs.getInt("sub_product_number"));
				setProducts.add(setProduct);
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
		return setProducts;
	}
	
	public int deleteSetProduct(String productCode) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "delete from tbl_set_product where set_product_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, productCode);
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
	
	public int deleteSetProductByProductId(int productId) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "delete from tbl_set_product where set_product_code = (select product_code from tbl_product where id = ?)";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, productId);
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
}
