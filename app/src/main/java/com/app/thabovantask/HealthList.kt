package com.app.thabovantask

data class HealthList(
    val statusCode: Int,
    val success: Boolean,
    val data: DataList,
) {
    data class DataList(
        val message: String,
        val health: ArrayList<Health>
    ) {
        data class Health(
            val name: String,
            val accessible: ArrayList<AccessibleList>
        ) {
            data class AccessibleList(
                val type: String,
                var success: Boolean,
                val message: String,
                val name: String,
                val api: String
            )
        }
    }
}
