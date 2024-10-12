import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aplication.purespace.ui.theme.PureSpaceTheme

@Composable
fun LoginScreen() {
    // Variables de estado para almacenar correo y contraseña
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginSuccess by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Iniciar Sesión", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Simulación de un login exitoso
                if (email == "usuario@ejemplo.com" && password == "123456") {
                    loginSuccess = true
                    loginError = ""
                } else {
                    loginError = "Credenciales incorrectas"
                    loginSuccess = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar mensaje de error si el login falla
        if (loginError.isNotEmpty()) {
            Text(text = loginError, color = MaterialTheme.colorScheme.error)
        }

        // Mostrar mensaje de éxito si el login es correcto
        if (loginSuccess) {
            Text(text = "Login Exitoso!", color = MaterialTheme.colorScheme.primary)
        }
    }
}

// Vista previa en el editor de Android Studio
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
