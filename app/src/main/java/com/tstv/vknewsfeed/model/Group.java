
package com.tstv.vknewsfeed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tstv.vknewsfeed.model.attachment.Link;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Group extends RealmObject implements Owner{

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("is_closed")
    @Expose
    private Integer isClosed;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("is_admin")
    @Expose
    private Integer isAdmin;
    @SerializedName("is_member")
    @Expose
    private Integer isMember;
    @SerializedName("photo_50")
    @Expose
    private String photo50;
    @SerializedName("photo_100")
    @Expose
    private String photo100;
    @SerializedName("photo_200")
    @Expose
    private String photo200;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("site")
    @Expose
    public String site;

    @SerializedName("links")
    RealmList<Link> links;

    @SerializedName("contacts")
    RealmList<Contact> contactList;



    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Integer isClosed) {
        this.isClosed = isClosed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public String getPhoto50() {
        return photo50;
    }

    public void setPhoto50(String photo50) {
        this.photo50 = photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public String getPhoto200() {
        return photo200;
    }

    public void setPhoto200(String photo200) {
        this.photo200 = photo200;
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public String getPhoto() {
        return photo100;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public RealmList<Link> getLinks() {
        return links;
    }

    public void setLinks(RealmList<Link> links) {
        this.links = links;
    }

    public RealmList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(RealmList<Contact> contactList) {
        this.contactList = contactList;
    }
}
