/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package portalbeans;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@XmlRootElement
public class UserPortal implements Serializable{
    
    private String userId;
    
    private String userName;
    
    private String userType;
    
    private String userIdCard;
    
    private String userPin;
    
    private String UserNo;
    
    private String status;
    
    private String branchId;
    
    private String branchName;
    
    
    
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }
    
    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    /**
     * @return the userIdCard
     */
    public String getUserIdCard() {
        return userIdCard;
    }
    
    /**
     * @param userIdCard the userIdCard to set
     */
    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }
    
    /**
     * @return the userPin
     */
    public String getUserPin() {
        return userPin;
    }
    
    /**
     * @param userPin the userPin to set
     */
    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }
    
    
    
    /**
     * @return the UserNo
     */
    public String getUserNo() {
        return UserNo;
    }
    
    /**
     * @param UserNo the UserNo to set
     */
    public void setUserNo(String UserNo) {
        this.UserNo = UserNo;
    }
    
    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }
    
    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    
    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }
    
    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
