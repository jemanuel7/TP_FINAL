import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.initialize
import com.google.firebase.storage.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {

    // Estado de las calorías, pasos y progreso
    private val _caloriesConsumed = MutableStateFlow(0)
    val caloriesConsumed = _caloriesConsumed.asStateFlow()

    private val _caloriesBurned = MutableStateFlow(0)
    val caloriesBurned = _caloriesBurned.asStateFlow()

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    private val _progress = MutableStateFlow(0f)
    val progress = _progress.asStateFlow()

    // Instancia de Firestore
    private val db = Firebase.firestore

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        db.collection("resumen")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    for (result in document) {
                        _caloriesConsumed.value = result.getLong("caloriesConsumed")?.toInt() ?: 0
                        _caloriesBurned.value = result.getLong("caloriesBurned")?.toInt() ?: 0
                        _steps.value = result.getLong("steps")?.toInt() ?: 0
                        _progress.value = result.getDouble("progress")?.toFloat() ?: 0f
                    }
                } else {
                    // Manejar el caso donde el documento no existe
                    println("No se encontró el documento.")
                }
            }
            .addOnFailureListener { exception ->
                // Manejo de errores
                println("Error al obtener datos: ${exception.message}")
            }
    }
}