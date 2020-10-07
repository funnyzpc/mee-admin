package com.mee.core.configuration;

import com.mee.sys.entity.SysUser;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.MergableAuthenticationInfo;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.subject.MutablePrincipalCollection;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MeeAuthenticationInfo implements MergableAuthenticationInfo, SaltedAuthenticationInfo {

    private PrincipalCollection principals;
    private Object credentials;
    private ByteSource credentialsSalt;
    private String showName;


    public MeeAuthenticationInfo() {
    }

    public MeeAuthenticationInfo(Object principal, Object credentials, String realmName) {
        this.principals = new SimplePrincipalCollection(principal, realmName);
        this.credentials = credentials;
    }

    public MeeAuthenticationInfo(SysUser sysUser, String realmName) {
        this.principals = new SimplePrincipalCollection(sysUser.getUser_name(), realmName);
        this.credentials = sysUser.getPassword();
        this.showName = sysUser.getNick_name();
    }

    public MeeAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName) {
        this.principals = new SimplePrincipalCollection(principal, realmName);
        this.credentials = hashedCredentials;
        this.credentialsSalt = credentialsSalt;
    }

    public MeeAuthenticationInfo(PrincipalCollection principals, Object credentials) {
        this.principals = new SimplePrincipalCollection(principals);
        this.credentials = credentials;
    }

    public MeeAuthenticationInfo(PrincipalCollection principals, Object hashedCredentials, ByteSource credentialsSalt) {
        this.principals = new SimplePrincipalCollection(principals);
        this.credentials = hashedCredentials;
        this.credentialsSalt = credentialsSalt;
    }

    @Override
    public ByteSource getCredentialsSalt() {
        return null;
    }

    @Override
    public PrincipalCollection getPrincipals() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }


    public void setPrincipals(PrincipalCollection principals) {
        this.principals = principals;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    public void setCredentialsSalt(ByteSource credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Override
    public void merge(AuthenticationInfo info) {
        if (info != null && info.getPrincipals() != null && !info.getPrincipals().isEmpty()) {
            if (this.principals == null) {
                this.principals = info.getPrincipals();
            } else {
                if (!(this.principals instanceof MutablePrincipalCollection)) {
                    this.principals = new SimplePrincipalCollection(this.principals);
                }

                ((MutablePrincipalCollection)this.principals).addAll(info.getPrincipals());
            }

            if (this.credentialsSalt == null && info instanceof SaltedAuthenticationInfo) {
                this.credentialsSalt = ((SaltedAuthenticationInfo)info).getCredentialsSalt();
            }

            Object thisCredentials = this.getCredentials();
            Object otherCredentials = info.getCredentials();
            if (otherCredentials != null) {
                if (thisCredentials == null) {
                    this.credentials = otherCredentials;
                } else {
                    if (!(thisCredentials instanceof Collection)) {
                        Set newSet = new HashSet();
                        newSet.add(thisCredentials);
                        this.setCredentials(newSet);
                    }

                    Collection credentialCollection = (Collection)this.getCredentials();
                    if (otherCredentials instanceof Collection) {
                        credentialCollection.addAll((Collection)otherCredentials);
                    } else {
                        credentialCollection.add(otherCredentials);
                    }

                }
            }
        }
    }
}
