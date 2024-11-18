package com.example.composebasics

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.os.Bundle
import android.view.animation.BounceInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp() {
        var shouldShowGreetings by rememberSaveable {
            mutableStateOf(true)
        }
        if (shouldShowGreetings) {
            Onboardings { shouldShowGreetings = !shouldShowGreetings }
        } else {
            Greetings()
        }

    }

    @Composable
    fun Onboardings(onContinueClicked: () -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to compose basics", modifier = Modifier.padding(16.dp))
            Button(
                onClick = { onContinueClicked() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(text = "Continue")
            }
        }
    }

    @Composable
    fun Greetings(names: List<String> = List<String>(1000){it.toString()}){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                LazyColumn {
                    item { Text(text = "Greetings", modifier = Modifier.padding(8.dp))  }
                    items(names) {
                        Greeting(it)
                    }
                }
            }
            }
        }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        var isExpanded by remember {
            mutableStateOf(false)
        }
        val extraPadding by animateDpAsState(
            targetValue =
            if (isExpanded) 48.dp else 16.dp,
            label = "",
            animationSpec = spring(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioHighBouncy)
        )

        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            Row(modifier = Modifier.padding(4.dp)) {
                Column(
                    modifier = modifier
                        .weight(1f)
                        .padding( start = 16.dp,top= 16.dp,bottom = extraPadding.coerceAtLeast(16.dp))
                ) {
                    Text(
                        text = "Hello !",
                    )
                    Text(
                        text = "$name Compose",
                        style= MaterialTheme.typography.titleLarge.copy(
                            fontSize = 22.sp
                        )
                    )

                }
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                        contentDescription = null
                    )

                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MyAppPreview() {
        ComposeBasicsTheme {
            MyApp()
        }
    }
    @Preview(showBackground = true, widthDp = 320, heightDp = 320)
    @Composable
    fun GreetingPreview() {
        ComposeBasicsTheme {
          Greetings()
        }
    }

    @Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_MASK)
    @Composable
    fun OnboardingPreview() {
        ComposeBasicsTheme {
            Onboardings(onContinueClicked = { })
        }
    }
}