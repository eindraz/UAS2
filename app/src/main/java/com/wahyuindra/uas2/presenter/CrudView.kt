package com.wahyuindra.uas2.presenter

import com.wahyuindra.uas2.model.DataItem

interface CrudView {

    //Untuk get data
    fun onSuccessGet(data: List<DataItem>?)
    fun onFailedGet(msg: String)

    //Untuk Delete
    fun onSuccessDelete(msg: String)
    fun onErrorDelete(msg: String)

    //Untuk Add
    fun successAdd(msg : String)
    fun errorAdd(msg: String)

    //Untuk Update
    fun onSuccessUpdate(msg : String)
    fun onErrorUpdate(msg : String)

}