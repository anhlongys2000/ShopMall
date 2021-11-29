/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CategoryDTO;
import dto.RauDTO;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class RauDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public UserDTO checkLogin(String username, String password) throws SQLException {
        try {
            cn = utils.DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT UserID,UserName,RoleID\n"
                        + "FROM tblUsers\n"
                        + "WHERE UserName=? and PassWord=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    UserDTO u = new UserDTO(rs.getInt("UserID"), rs.getString("UserName"), "****", rs.getInt("RoleID"));
                    return u;
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return null;
    }

    public ArrayList<RauDTO> getListRau() throws SQLException {
        ArrayList<RauDTO> list = new ArrayList<>();
        try {
            cn = utils.DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT RauID,RauName,image,Price,quantity,description,CategoryID\n"
                        + "from tblRau\n";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new RauDTO(rs.getInt("RauID"),
                            rs.getString("RauName"),
                            rs.getString("image"),
                            rs.getFloat("Price"),
                            rs.getInt("quantity"),
                            rs.getString("description"),
                            rs.getInt("CategoryID")));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public ArrayList<CategoryDTO> getListCategory() throws SQLException {
        ArrayList<CategoryDTO> list = new ArrayList<>();
        try {
            cn = utils.DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT CategoryID,CategoryName\n"
                        + "from tblCategory\n";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new CategoryDTO(rs.getInt("CategoryID"),
                            rs.getString("CategoryName")));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public ArrayList<RauDTO> searchByName(String name) throws SQLException {
        ArrayList<RauDTO> list = new ArrayList<>();
        try {
            cn = utils.DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT RauID,RauName,image,Price,quantity,description,CategoryID\n"
                        + "from tblRau\n"
                        + "WHERE RauName like ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new RauDTO(rs.getInt("RauID"),
                            rs.getString("RauName"),
                            rs.getString("image"),
                            rs.getFloat("Price"),
                            rs.getInt("quantity"),
                            rs.getString("description"),
                            rs.getInt("CategoryID")));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public RauDTO getListbyID(int id) throws SQLException {
        RauDTO list = new RauDTO();
        try {
            cn = utils.DBUtil.makeConnection();
            if (cn != null) {
                String sql = "SELECT RauID,RauName,image,Price,quantity,description,CategoryID\n"
                        + "from tblRau\n"
                        + "WHERE RauID = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    list = new RauDTO(rs.getInt("RauID"),
                            rs.getString("RauName"),
                            rs.getString("image"),
                            rs.getFloat("Price"),
                            rs.getInt("quantity"),
                            rs.getString("description"),
                            rs.getInt("CategoryID"));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public int edit(RauDTO b) throws SQLException {
        try {
            cn = utils.DBUtil.makeConnection();
            if (cn != null) {
                String sql = "UPDATE tblRau\n"
                        + "SET RauName=?,image=?,Price=?,quantity=?,description=?,CategoryID=?\n"
                        + "WHERE RauID=?";

                pst = cn.prepareStatement(sql);
                pst.setString(1, b.getRauName());
                pst.setString(2, b.getImage());
                pst.setFloat(3, b.getPrice());
                pst.setInt(4, b.getQuantity());
                pst.setString(5, b.getDescription());
                pst.setInt(6, b.getCategoryID());
                pst.setInt(7, b.getRauID());
                return pst.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return -1;
    }

    public ArrayList<RauDTO> getListbyCate(int cid) throws SQLException {
        ArrayList<RauDTO> list = new ArrayList<>();
        try {
            cn = utils.DBUtil.makeConnection();
            if (cn != null) {
                String sql = "";
                if (cid == 0) {
                    sql = "SELECT RauID,RauName,image,Price,quantity,description,CategoryID\n"
                            + "from tblRau";

                } else {
                    sql = "SELECT RauID,RauName,image,Price,quantity,description,CategoryID\n"
                            + "from tblRau\n"
                            + "WHERE CategoryID = ?";
                }
                pst = cn.prepareStatement(sql);
                pst.setInt(1, cid);
                rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new RauDTO(rs.getInt("RauID"),
                            rs.getString("RauName"),
                            rs.getString("image"),
                            rs.getFloat("Price"),
                            rs.getInt("quantity"),
                            rs.getString("description"),
                            rs.getInt("CategoryID")));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public int insert(RauDTO b) throws SQLException {
        try {
            cn = utils.DBUtil.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO tblRau\n"
                        + "VALUE (?,?,?,?,?,?)";

                pst = cn.prepareStatement(sql);
                pst.setString(1, b.getRauName());
                pst.setString(2, b.getImage());
                pst.setFloat(3, b.getPrice());
                pst.setInt(4, b.getQuantity());
                pst.setString(5, b.getDescription());
                pst.setInt(6, b.getCategoryID());
                return pst.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return -1;
    }

    public int getQuantityOfBanh(int bid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
