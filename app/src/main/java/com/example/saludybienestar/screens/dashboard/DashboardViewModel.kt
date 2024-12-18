import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DashboardViewModel : ViewModel() {

    // Estados de las calorías, pasos y progreso
    private val _caloriesConsumed = MutableStateFlow(0)
    val caloriesConsumed = _caloriesConsumed.asStateFlow()

    private val _caloriesBurned = MutableStateFlow(0)
    val caloriesBurned = _caloriesBurned.asStateFlow()

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    private val _progress = MutableStateFlow(0f)
    val progress = _progress.asStateFlow()

    // Instancia de Firestore
    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    init {
        // Verifica la inicialización de Firebase
        FirebaseApp.initializeApp(FirebaseApp.getInstance().applicationContext)
        loadDashboardData()
    }

    private fun loadDashboardData() {
        // Lanzamos la operación asincrónica dentro de una coroutine
        viewModelScope.launch {
            try {
                // Obtén los documentos de Firestore de manera asíncrona
                val snapshot = db.collection("resumen").get().await()

                // Procesa los documentos obtenidos
                snapshot.documents.forEach { document ->
                    // Actualiza los valores de StateFlow con los datos obtenidos
                    _caloriesConsumed.emit(document.getString("calorias_consumidas")?.toInt() ?: 0)
                    _caloriesBurned.emit(document.getString("calorias_quemadas")?.toInt() ?: 0)
                    _steps.emit(document.getString("pasos")?.toInt() ?: 0)
                    _progress.emit(document.getString("pregreso")?.toFloat() ?: 0f)
                }
            } catch (e: Exception) {
                println("Error al obtener los documentos: ${e.message}")
            }
        }
    }
}