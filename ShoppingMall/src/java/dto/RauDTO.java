/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author asus
 */
public class RauDTO implements Serializable {

    int RauID;
    String RauName;
    String image;
    float price;
    int quantity;
    String description;
    int CategoryID;

    public RauDTO() {
    }

    public RauDTO(int RauID, String RauName, String image, float price, int quantity, String description, int CategoryID) {
        this.RauID = RauID;
        this.RauName = RauName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.CategoryID = CategoryID;
    }
    
    public int getRauID() {
        return RauID;
    }

    public void setRauID(int RauID) {
        this.RauID = RauID;
    }

    public String getRauName() {
        return RauName;
    }

    public void setRauName(String RauName) {
        this.RauName = RauName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    @Override
    public String toString() {
        return "RauDTO{" + "RauID=" + RauID + ", RauName=" + RauName + ", image=" + image + ", price=" + price + ", quantity=" + quantity + ", description=" + description + ", CategoryID=" + CategoryID + '}';
    }
    
    
}
