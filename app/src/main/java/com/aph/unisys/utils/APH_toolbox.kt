package com.aph.unisys.utils

import android.util.Log
import java.text.SimpleDateFormat

class APH_toolbox {
    companion object{
        fun Fix_Date_Cutom(date_and_hour: String, InputFormat: String, OutputFormat: String):String{
            var result = date_and_hour
            try {
                val dateFormatInput = SimpleDateFormat(InputFormat) //this format changeable according to your choice
                val dateFormatOutput = SimpleDateFormat(OutputFormat) //this format changeable according to your choice
                if(date_and_hour==""||date_and_hour=="-"){
                    return ""
                }
                val data=dateFormatInput.parse(date_and_hour)

                result=""
                if(data!=null){
                    result=dateFormatOutput.format(data)
                }
            }
            catch(e:Exception){
                Log.e("Fix_Date_Cutom",e.message.toString())

            }


            return result

        }
    }
}