package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Depot;
import com.medicine.entities.Grid;
import com.medicine.entities.Layer;
import com.medicine.entities.Locator;
import com.medicine.entities.Reservoir;
import com.medicine.entities.Shelf;
import com.medicine.util.MedicineRuntimeException;

public class LocatorDAO{
	
	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public List<Depot> findAllLocators(){
		List<Depot> depots = new ArrayList<Depot>();
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id,locator_code,locator_name,depot_number,reservoir_number,shelf_number,layer_number,grid_number,grid_length,"
				+ "grid_width,grid_heigth,grid_volume,grid_weigth "
				+ "from tbl_locator";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Depot depot = new Depot();
				depot.setDepotNumber(rs.getInt("depot_number"));
				depot.getReservoirs().get(0).setReservoirNumber("reservoir_number");
				
				depots.add(depot);
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
		return depots;
	}
	
	public List<Grid> findAllGrids(int layerNumber, String shelfNumber, String reservoirNumber, int depotNumber){
		List<Grid> grids = new ArrayList<Grid>();
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " select count(grid_number) grid_count, grid_number, layer_number,shelf_number,reservoir_number,depot_number,"
				+ "locator_code,locator_name,grid_length,grid_width,grid_heigth,grid_volume,grid_weigth "
				+ "from tbl_locator "
				+ "group by grid_number, layer_number,shelf_number,reservoir_number,depot_number "
				+ "having layer_number = ? and shelf_number = ? and depot_number = ? and reservoir_number = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, layerNumber);
			stmt.setString(2, shelfNumber);
			stmt.setInt(3, depotNumber);
			stmt.setString(4, reservoirNumber);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Grid grid = new Grid();
				Locator locator = new Locator();
				grid.setGridNumber(rs.getInt("grid_number"));
				locator.setLocatorCode(rs.getString("locator_code"));
				locator.setLocatorName(rs.getString("locator_name"));
				locator.setGridLength(rs.getInt("grid_length"));
				locator.setGridWidth(rs.getInt("grid_width"));
				locator.setGridHeigth(rs.getInt("grid_heigth"));
				locator.setGridVolume(rs.getInt("grid_volume"));
				locator.setGridWeigth(rs.getInt("grid_weigth"));
				grid.setLocator(locator);
				grids.add(grid);
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
		return grids;
	}
	
	public List<Layer> findAllLayers(String shelfNumber,String reservoirNumber, int depotNumber){
		List<Layer> layers = new ArrayList<Layer>();
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(layer_number), layer_number,shelf_number,reservoir_number,depot_number "
				+ "from tbl_locator "
				+ "group by layer_number,shelf_number,reservoir_number,depot_number "
				+ "having shelf_number = ? and depot_number = ? and reservoir_number = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, shelfNumber);
			stmt.setInt(2, depotNumber);
			stmt.setString(3, reservoirNumber);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Layer layer = new Layer();
				layer.setLayerNumber(rs.getInt("layer_number"));
				layers.add(layer);
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
		return layers;
	}
	
	public List<Shelf> findAllShelfs(String reservoirNumber, int depotNumber){
		List<Shelf> shelfs = new ArrayList<Shelf>();
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(shelf_number) shelf_count, shelf_number,reservoir_number,depot_number "
				+ "from tbl_locator "
				+ "group by shelf_number,reservoir_number,depot_number "
				+ "having depot_number = ? and reservoir_number = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, depotNumber);
			stmt.setString(2, reservoirNumber);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Shelf shelf = new Shelf();
				shelf.setShelfNumber(rs.getString("shelf_number"));
				shelf.setShelfCount(rs.getInt("shelf_count"));
				shelfs.add(shelf);
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
		return shelfs;
	}
	
	public List<Reservoir> findAllReservoirs(Depot depot){
		List<Reservoir> reservoirs = new ArrayList<Reservoir>();
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(reservoir_number) reservoir_count,reservoir_number,depot_number "
				+ "from tbl_locator "
				+ "group by reservoir_number,depot_number "
				+ "having depot_number = ? ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, depot.getDepotNumber());
			rs = stmt.executeQuery();
			while (rs.next()) {
				Reservoir reservoir = new Reservoir();
				reservoir.setReservoirNumber(rs.getString("reservoir_number"));
				reservoir.setReservoirCount(rs.getInt("reservoir_count"));
				
				reservoirs.add(reservoir);
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
		return reservoirs;
	}
	
	public List<Depot> findAllDepots(){
		List<Depot> depots = new ArrayList<Depot>();
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(depot_number) depot_count,depot_number "
				+ "from tbl_locator "
				+ "group by depot_number";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Depot depot = new Depot();
				depot.setDepotNumber(rs.getInt("depot_number"));
				depot.setDepotCount(rs.getInt("depot_count"));
				
				depots.add(depot);
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
		return depots;
	}
	
	public int addLocator(Locator locator){
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_locator (locator_code,shelf_number,layer_number,grid_volume,locator_desc) "
				+ "values (?,?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, locator.getLocatorCode());
			stmt.setString(2,locator.getShelfNumber());
			stmt.setInt(3, locator.getLayerNumber());
			stmt.setInt(4, locator.getGridVolume());
			stmt.setString(5, locator.getLocatorDesc());
			stmt.executeUpdate();
			count ++;
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
	
	public List<Locator> queryAllLocators(String shelfNumber){
		List<Locator> locators = new ArrayList<Locator>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select locator_code,shelf_number,layer_number,grid_volume,locator_desc "
				+ "from tbl_locator "
				+ "where shelf_number =?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, shelfNumber);
			rs = stmt.executeQuery();
			while(rs.next()){
				Locator locator = new Locator();
				locator.setLocatorCode(rs.getString("locator_code"));
				locator.setShelfNumber(rs.getString("shelf_number"));
				locator.setLayerNumber(rs.getInt("layer_number"));
				locator.setGridVolume(rs.getInt("grid_volume"));
				locator.setLocatorDesc(rs.getString("locator_desc"));
				locators.add(locator);
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
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
		return locators;
	}
}
