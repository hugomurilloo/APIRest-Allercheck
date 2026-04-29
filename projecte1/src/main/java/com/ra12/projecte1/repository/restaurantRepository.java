package com.ra12.projecte1.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ra12.projecte1.model.restaurantModel;

/**
 * Repository per gestionar les operacions CRUD amb la base de dades utilitzant JdbcTemplate.
 * Implementa les operacions bàsiques: create, read, update, delete.
 */
@Repository
public class restaurantRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Insereix un nou restaurant a la base de dades.
     */
    public int save(restaurantModel restaurant) {
        String sql = "INSERT INTO restaurant (name, rating, is_verified, sense_gluten, sense_lactosa, " +
                     "sense_fruits_secs, sense_marisc, es_vega, es_halal, es_kosher, te_ou, " +
                     "tipus_cuina, rang_preu, ubicacio, telefon, data_created, data_updated) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        return jdbcTemplate.update(sql,
            restaurant.getName(),
            restaurant.getRating(),
            restaurant.getIsVerified(),
            restaurant.getSenseGluten(),
            restaurant.getSenseLactosa(),
            restaurant.getSenseFruitsSecs(),
            restaurant.getSenseMarisc(),
            restaurant.getEsVega(),
            restaurant.getEsHalal(),
            restaurant.getEsKosher(),
            restaurant.getTeOu(),
            restaurant.getTipusCuina(),
            restaurant.getRangPreu(),
            restaurant.getUbicacio(),
            restaurant.getTelefon(),
            restaurant.getDataCreated(),
            restaurant.getDataUpdated()
        );
    }

    /**
     * Retorna tots els restaurants de la base de dades.
     */
    public List<restaurantModel> findAll() {
        String sql = "SELECT * FROM restaurant";
        return jdbcTemplate.query(sql, new restaurantRowMapper());
    }

    /**
     * Busca un restaurant pel seu ID.
     */
    public restaurantModel findById(Long id) {
        String sql = "SELECT * FROM restaurant WHERE id = ?";
        List<restaurantModel> results = jdbcTemplate.query(sql, new restaurantRowMapper(), id);
        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * Actualitza un restaurant existent.
     */
    public int update(restaurantModel restaurant) {
        String sql = "UPDATE restaurant SET name = ?, rating = ?, is_verified = ?, sense_gluten = ?, " +
                     "sense_lactosa = ?, sense_fruits_secs = ?, sense_marisc = ?, es_vega = ?, " +
                     "es_halal = ?, es_kosher = ?, te_ou = ?, tipus_cuina = ?, rang_preu = ?, " +
                     "ubicacio = ?, telefon = ?, data_updated = ? WHERE id = ?";
        
        return jdbcTemplate.update(sql,
            restaurant.getName(),
            restaurant.getRating(),
            restaurant.getIsVerified(),
            restaurant.getSenseGluten(),
            restaurant.getSenseLactosa(),
            restaurant.getSenseFruitsSecs(),
            restaurant.getSenseMarisc(),
            restaurant.getEsVega(),
            restaurant.getEsHalal(),
            restaurant.getEsKosher(),
            restaurant.getTeOu(),
            restaurant.getTipusCuina(),
            restaurant.getRangPreu(),
            restaurant.getUbicacio(),
            restaurant.getTelefon(),
            restaurant.getDataUpdated(),
            restaurant.getId()
        );
    }

    /**
     * Elimina un restaurant pel seu ID.
     */
    public int deleteById(Long id) {
        String sql = "DELETE FROM restaurant WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * Elimina tots els restaurants de la base de dades.
     */
    public int deleteAll() {
        String sql = "DELETE FROM restaurant";
        return jdbcTemplate.update(sql);
    }

    /**
     * Classe interna per mapejar les files del ResultSet a objectes restaurantModel.
     */
    private static class restaurantRowMapper implements RowMapper<restaurantModel> {
        @Override
        public restaurantModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            restaurantModel restaurant = new restaurantModel();
            restaurant.setId(rs.getLong("id"));
            restaurant.setName(rs.getString("name"));
            restaurant.setRating(rs.getDouble("rating"));
            restaurant.setIsVerified(rs.getBoolean("is_verified"));
            restaurant.setSenseGluten(rs.getBoolean("sense_gluten"));
            restaurant.setSenseLactosa(rs.getBoolean("sense_lactosa"));
            restaurant.setSenseFruitsSecs(rs.getBoolean("sense_fruits_secs"));
            restaurant.setSenseMarisc(rs.getBoolean("sense_marisc"));
            restaurant.setEsVega(rs.getBoolean("es_vega"));
            restaurant.setEsHalal(rs.getBoolean("es_halal"));
            restaurant.setEsKosher(rs.getBoolean("es_kosher"));
            restaurant.setTeOu(rs.getBoolean("te_ou"));
            restaurant.setTipusCuina(rs.getString("tipus_cuina"));
            restaurant.setRangPreu(rs.getString("rang_preu"));
            restaurant.setUbicacio(rs.getString("ubicacio"));
            restaurant.setTelefon(rs.getInt("telefon"));
            restaurant.setDataCreated(rs.getTimestamp("data_created").toLocalDateTime());
            restaurant.setDataUpdated(rs.getTimestamp("data_updated").toLocalDateTime());
            return restaurant;
        }
    }
}