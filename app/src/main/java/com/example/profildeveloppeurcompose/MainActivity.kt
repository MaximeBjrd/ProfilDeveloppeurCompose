package com.example.profildeveloppeurcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeurcompose.ui.theme.ProfilDeveloppeurComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurComposeTheme {
                ProfilScreen(
                    nom = "Maxime BEAUJARD",
                    role = "Alternant en Architecture Logicielle",
                    description = "En alternance, je cherche à renforcer mes bases en développement mobile avec Jetpack Compose.",
                    competences = listOf("Kotlin", "Jetpack Compose", "Git"),
                    disponibilite = "Disponible pour une alternance",
                    contact = "maxime@exemple.com"
                )
            }
        }
    }
}

@Composable
fun ProfilScreen(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    disponibilite: String,
    contact: String,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        ProfilCard(
            nom = nom,
            role = role,
            description = description,
            competences = competences,
            disponibilite = disponibilite,
            contact = contact,
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        )
    }
}

@Composable
fun ProfilCard(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    disponibilite: String,
    contact: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ProfilHeader(nom = nom, role = role)

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            )
            competences.forEach { competence ->
                SkillItem(competence = competence)
            }

            InfoSecondaire(
                libelle = "Contact",
                valeur = contact,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )

            ActionZone(
                texte = disponibilite,
                descriptionAccessibilite = "Voir la disponibilité de $nom",
                onClick = { /* TODO : afficher les détails de disponibilité */ },
                modifier = Modifier.padding(top = 16.dp)
            )

            ActionButton(
                texte = "Contacter",
                onClick = { /* TODO : contacter le développeur */ },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ProfilHeader(nom: String, role: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = nom, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SkillItem(competence: String, modifier: Modifier = Modifier) {
    Text(
        text = "• $competence",
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier.padding(vertical = 2.dp)
    )
}

@Composable
fun InfoSecondaire(libelle: String, valeur: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start) {
    Text(
        text = "$libelle : $valeur",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun ActionZone(texte: String, descriptionAccessibilite: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .semantics {
                role = Role.Button
                contentDescription = descriptionAccessibilite
            },
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = texte,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ActionButton(texte: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = texte)
    }
}

@Preview(showBackground = true, name = "Alternant disponible")
@Composable
fun ProfilScreenJuniorPreview() {
    ProfilDeveloppeurComposeTheme {
        ProfilScreen(
            nom = "Maxime BEAUJARD",
            role = "Alternant en Architecture Logicielle",
            description = "En alternance, je cherche à renforcer mes bases en développement mobile avec Jetpack Compose.",
            competences = listOf("Kotlin", "Jetpack Compose", "Git"),
            disponibilite = "Disponible pour une alternance",
            contact = "maxime@exemple.com"
        )
    }
}

@Preview(showBackground = true, name = "Full-stack non disponible")
@Composable
fun ProfilScreenFullStackPreview() {
    ProfilDeveloppeurComposeTheme {
        ProfilScreen(
            nom = "Camille MARTIN",
            role = "Développeuse full-stack",
            description = "Cinq ans d'expérience sur des applications web et mobiles.",
            competences = listOf("Java", "Angular", "Spring Boot", "SQL"),
            disponibilite = "Non disponible avant janvier 2027",
            contact = "camille@exemple.com"
        )
    }
}