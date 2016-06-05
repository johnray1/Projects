/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author JOHN
 */
public class PaymentInvalidOp {
    
    private String validation;
    private String traId;

    public PaymentInvalidOp() {
    }

    public PaymentInvalidOp(String validation, String traId) {
        this.validation = validation;
        this.traId = traId;
    }

    /**
     * @return the validation
     */
    public String getValidation() {
        return validation;
    }

    /**
     * @param validation the validation to set
     */
    public void setValidation(String validation) {
        this.validation = validation;
    }

    /**
     * @return the traId
     */
    public String getTraId() {
        return traId;
    }

    /**
     * @param traId the traId to set
     */
    public void setTraId(String traId) {
        this.traId = traId;
    }
    
    
    
}
