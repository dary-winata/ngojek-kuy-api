package aej.finalproject.ngojekkuy.user.repo.user

import aej.finalproject.ngojekkuy.user.model.user.UserDatabase
import aej.finalproject.ngojekkuy.user.model.user.UserRequest
import aej.finalproject.ngojekkuy.user.model.user.UserUpdate

interface UserRepo {
    fun getUserByUsername(username: String): UserDatabase
    fun addUser(userRequest: UserRequest, role: String): UserDatabase
    fun editUser(userUpdate: UserUpdate, id: String): UserDatabase
    fun getUserById(id: String): UserDatabase
}