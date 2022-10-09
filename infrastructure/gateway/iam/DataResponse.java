package api.shared.infrastructure.gateway.iam;

import java.util.List;

public class DataResponse {

    private String userId;
    private String clientId;
    private String roleId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private List<String> scopes;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public String toString() {
        return "DataResponse ["
            + "userId=" + userId
            + ", clientId=" + clientId
            + ", roleId=" + roleId
            + ", userFirstName=" + userFirstName
            + ", userLastName=" + userLastName
            + ", userEmail=" + userEmail
            + ", userPassword=" + userPassword
            + ", scopes=" + scopes
            + "]";
    }
}
