import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aplication.purespace.R
import com.aplication.purespace.ui.register.view.RegisterScreen
import com.aplication.purespace.ui.theme.Cremita
import com.aplication.purespace.ui.theme.PureSpaceTheme
import com.aplication.purespace.ui.theme.White
import com.aplication.purespace.ui.theme.skyBlue
import com.google.firebase.auth.FirebaseAuth
import kotlin.concurrent.timerTask

@Composable
fun LoginScreen(navigateToHome: () -> Unit, navigateToRegister: () -> Unit, auth: FirebaseAuth) {
    // Variables de estado para almacenar correo y contraseña
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
            .background(Brush.verticalGradient(listOf(skyBlue, White))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Agregar imagen
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")
        Spacer(modifier = Modifier.height(16.dp))
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
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        Log.i("Login", "Login successful")
                        navigateToHome()
                    }else{
                        Log.i("Login", "Login failed")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar Sesión")
        }
        Button(
            onClick = {
                navigateToRegister()
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
