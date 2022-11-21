package com.example.foodapp.Model

class Users(var username:String?=null,
            var mail:String?=null,
            var password:String?=null,
            var userId:String?=null) {
    class Users constructor(username: String, mail: String, password: String) {

    }

    constructor(userId: String) : this() {
        this.userId = userId
    }

    constructor() : this(null) {}

    @JvmName("getUsername1")
    public fun getUsername(): String {
        return username.toString()
    }

    @JvmName("setUsername1")
    public fun setUsername(username: String) {
        this.username = username

    }

    @JvmName("getMail1")
    public fun getMail(): String {
        return mail.toString()
    }

    @JvmName("setMail1")
    public fun setMail(mail: String) {
        this.mail = mail
    }

    @JvmName("getpassword1")
    public fun getpassword(): String {
        return password.toString()
    }

    @JvmName("setpassword1")
    public fun setpassword(password: String) {
        this.password = password
    }

    @JvmName("getUserId1")
    public fun getUserId(key: String?): String {
        return userId.toString()
    }

    @JvmName("setUserId1")
    public fun setUserId(userId: String) {
        this.userId = userId
    }
}