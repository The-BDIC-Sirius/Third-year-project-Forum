package com.eden.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "BBS_User", schema = "test", catalog = "")
public class BbsUserEntity {
    private int id;
    private String nickname;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String headUri;
    private String introducation;
    private String signature;
    private Integer topicCount;
    private Integer replyCount;
    private Integer bestTopicCount;
    private Integer lastTopicId;
    private Integer lastReplyId;
    private Integer amount;
    private Boolean enabled;
    private Timestamp createdOn;
    private UserEntity userById;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname", nullable = false, length = 100)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 1000)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "HeadUri", nullable = true, length = 800)
    public String getHeadUri() {
        return headUri;
    }

    public void setHeadUri(String headUri) {
        this.headUri = headUri;
    }

    @Basic
    @Column(name = "Introducation", nullable = true, length = 800)
    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    @Basic
    @Column(name = "Signature", nullable = true, length = 4000)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Basic
    @Column(name = "TopicCount", nullable = true)
    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
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
    @Column(name = "BestTopicCount", nullable = true)
    public Integer getBestTopicCount() {
        return bestTopicCount;
    }

    public void setBestTopicCount(Integer bestTopicCount) {
        this.bestTopicCount = bestTopicCount;
    }

    @Basic
    @Column(name = "LastTopicID", nullable = true)
    public Integer getLastTopicId() {
        return lastTopicId;
    }

    public void setLastTopicId(Integer lastTopicId) {
        this.lastTopicId = lastTopicId;
    }

    @Basic
    @Column(name = "LastReplyID", nullable = true)
    public Integer getLastReplyId() {
        return lastReplyId;
    }

    public void setLastReplyId(Integer lastReplyId) {
        this.lastReplyId = lastReplyId;
    }

    @Basic
    @Column(name = "Amount", nullable = true)
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
    @Column(name = "CreatedON", nullable = true)
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BbsUserEntity that = (BbsUserEntity) o;
        return id == that.id &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(headUri, that.headUri) &&
                Objects.equals(introducation, that.introducation) &&
                Objects.equals(signature, that.signature) &&
                Objects.equals(topicCount, that.topicCount) &&
                Objects.equals(replyCount, that.replyCount) &&
                Objects.equals(bestTopicCount, that.bestTopicCount) &&
                Objects.equals(lastTopicId, that.lastTopicId) &&
                Objects.equals(lastReplyId, that.lastReplyId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(enabled, that.enabled) &&
                Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nickname, firstName, lastName, password, email, headUri, introducation, signature, topicCount, replyCount, bestTopicCount, lastTopicId, lastReplyId, amount, enabled, createdOn);
    }

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "id", nullable = false)
    public UserEntity getUserById() {
        return userById;
    }

    public void setUserById(UserEntity userById) {
        this.userById = userById;
    }
}
