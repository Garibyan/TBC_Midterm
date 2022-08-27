package com.garibyan.armen.tbc_midterm.view.auth

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.ref.WeakReference

object AuthenticationManager: FirebaseAuthentication{

    private var activity: WeakReference<Activity>? = null

    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    private var database: DatabaseReference = Firebase.database.reference

    fun initActivity(activity: Activity){
        this.activity = WeakReference(activity)
    }

    override fun logOut() {
        auth.signOut()
    }

    override fun saveUser(user:User,resultLambda: (Boolean) -> Unit) {
        database.child("users").child(auth.currentUser!!.uid).setValue(user)
        database.child("users").child(auth.currentUser!!.uid).get().addOnSuccessListener {
            UserInstance.userData = user
            resultLambda.invoke(true)
        }.addOnFailureListener{
            resultLambda.invoke(false)
        }
    }

    override fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun getUser(resultLambda: (Boolean) -> Unit) {
        database.child("users").get().addOnSuccessListener {
            UserInstance.userData = it.child(auth.currentUser!!.uid).getValue(User::class.java) as User
            resultLambda.invoke(true)
        }.addOnFailureListener{
            resultLambda.invoke(false)
        }
    }

    override fun updatePassword(password: String, resultLambda: (Boolean) -> Unit) {
        auth.currentUser?.updatePassword(password)?.addOnCompleteListener { task->
            resultLambda(task.isSuccessful)
        }
    }

    override fun passwordReset(email: String, resultLambda: (Boolean) -> Unit) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            resultLambda(task.isSuccessful)
        }
    }

    override fun login(email: String, password:String,resultLambda: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            resultLambda(task.isSuccessful)
        }
    }

    override fun register(user: User,password: String,resultLambda: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                saveUser(user,resultLambda)
            }else
                resultLambda(false)
        }
    }

}

interface FirebaseAuthentication{

    fun logOut()

    fun isLoggedIn(): Boolean

    fun getUser(resultLambda: (Boolean) -> Unit)

    fun saveUser(user:User,resultLambda: (Boolean) -> Unit)

    fun passwordReset(email: String,resultLambda: (Boolean) -> Unit)

    fun updatePassword(password: String, resultLambda: (Boolean) -> Unit)

    fun login(email: String, password:String,resultLambda: (Boolean) -> Unit)

    fun register(user: User,password: String,resultLambda: (Boolean) -> Unit)

}