package com.lnd.RencontreAfricaine

data class Users(val userData: UserData, val userStatue: UserStatue?, val userInfo: UserInfo, val userChats: UserChats?)

data class UserData(val id:String, val phone:String, var nom:String, var prenom:String, var age:Int, var sexe:String, val mdp:String, var imgProfileUrl:String,
                    val relation:String, val localisation:String, val language:String)

data class UserStatue(var nbrKey:Int, val connected:Boolean, val premiumDays:Int)

data class UserChats(val idChat:String, val name:String, val imgProfile: String, val toUserId:String, val lastMessage:String, val nbrNewMessage:Int, val connected: Boolean)

data class UserInfo(val contacts: Contacts, val searching: Searching, val info: Info?)

data class Contacts(var whatsapp:String, var messenger:String, var gmail:String)
data class Searching(val sexe: String, val relation:String, val age: String)
data class Info(var bio:String, var hobbies:MutableList<String>?)
//sexe: (Homme/Femme)

data class MessageInfo(val idChat: String, val fromUserId: String, val toUser: String, val message:String, val type:String)
//type : text, image

data class InfoServer(val adminPhone:String, val adminEmail: String, val isAvailable:Boolean, val minVersion:Int=0, val premuimPhone:String, val msgTitle:String, val msgDescription:String)