package com.nunclear.app.dao;

import com.nunclear.app.conexion.ConnS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {

    ConnS instance = ConnS.getInstance();
    Connection connection = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarProveedor(Proveedor pr) {
        String sql = "INSERT INTO proveedor(ruc, nombre, telefono, direccion) VALUES (?,?,?,?)";
        try {

            ps = connection.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                //connection.close();
                connection.prepareCall(sql);
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List ListarProveedor() {
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getString("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                Listapr.add(pr);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }

    public boolean EliminarProveedor(int id) {
        String sql = "DELETE FROM proveedor WHERE id = ? ";
        try {

            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                connection.prepareCall(sql);
                //connection.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean ModificarProveedor(Proveedor pr) {
        String sql = "UPDATE proveedor SET ruc=?, nombre=?, telefono=?, direccion=? WHERE id=?";
        try {

            ps = connection.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setInt(5, pr.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                connection.prepareCall(sql);
                //connection.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
