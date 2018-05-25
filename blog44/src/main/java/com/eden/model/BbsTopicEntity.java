package com.eden.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "BBS_Topic", schema = "test", catalog = "")
public class BbsTopicEntity {
    private int id;
    private int classId;
    private Integer specialId;
    private String title;
    private String body;
    private Boolean enabled;
    private Integer hits;
    private Integer replyCount;
    private Integer modifiedBy;
    private Timestamp modifiedOn;
    private Integer repliedBy;
    private Timestamp repliedOn;
    private Integer iconId;
    private Boolean isClose;
    private String imgUrl;
    private Timestamp createdOn;
    private String tap;
    private BbsUserEntity bbsUserByUserId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ClassID", nullable = false)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "SpecialID", nullable = true)
    public Integer getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Integer specialId) {
        this.specialId = specialId;
    }

    @Basic
    @Column(name = "Title", nullable = false, length = 300)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Body", nullable = true, length = -1)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "Enabled", nullable = true)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "Hits", nullable = true)
    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @Basic
    @Column(name = "ReplyCount", nullable = true)
    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    @Basic
    @Column(name = "ModifiedBy", nullable = true)
    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Basic
    @Column(name = "ModifiedOn", nullable = true)
    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Basic
    @Column(name = "RepliedBy", nullable = true)
    public Integer getRepliedBy() {
        return repliedBy;
    }

    public void setRepliedBy(Integer repliedBy) {
        this.repliedBy = repliedBy;
    }

    @Basic
    @Column(name = "RepliedOn", nullable = true)
    public Timestamp getRepliedOn() {
        return repliedOn;
    }

    public void setRepliedOn(Timestamp repliedOn) {
        this.repliedOn = repliedOn;
    }

    @Basic
    @Column(name = "IconID", nullable = true)
    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    @Basic
    @Column(name = "isClose", nullable = true)
    public Boolean getClose() {
        return isClose;
    }

    public void setClose(Boolean close) {
        isClose = close;
    }

    @Basic
    @Column(name = "ImgUrl", nullable = true, length = 400)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "CreatedOn", nullable = true)
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Basic
    @Column(name = "tap", nullable = true, length = 100)
    public String getTap() {
        return tap;
    }

    public void setTap(String tap) {
        this.tap = tap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BbsTopicEntity that = (BbsTopicEntity) o;
        return id == that.id &&
                classId == that.classId &&
                Objects.equals(specialId, that.specialId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(body, that.body) &&
                Objects.equals(enabled, that.enabled) &&
                Objects.equals(hits, that.hits) &&
                Objects.equals(replyCount, that.replyCount) &&
                Objects.equals(modifiedBy, that.modifiedBy) &&
                Objects.equals(modifiedOn, that.modifiedOn) &&
                Objects.equals(repliedBy, that.repliedBy) &&
                Objects.equals(repliedOn, that.repliedOn) &&
                Objects.equals(iconId, that.iconId) &&
                Objects.equals(isClose, that.isClose) &&
                Objects.equals(imgUrl, that.imgUrl) &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(tap, that.tap);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, classId, specialId, title, body, enabled, hits, replyCount, modifiedBy, modifiedOn, repliedBy, repliedOn, iconId, isClose, imgUrl, createdOn, tap);
    }

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID", nullable = false)
    public BbsUserEntity getBbsUserByUserId() {
        return bbsUserByUserId;
    }

    public void setBbsUserByUserId(BbsUserEntity bbsUserByUserId) {
        this.bbsUserByUserId = bbsUserByUserId;
    }
}
