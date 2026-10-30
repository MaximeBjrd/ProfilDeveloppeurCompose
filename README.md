# Profil développeur Compose

## Description

Cette application Android affiche une fiche « Profil développeur » réalisée avec Jetpack Compose.
L'écran présente un nom, un rôle, une courte description, une liste de compétences, un contact et une disponibilité.
Il propose deux actions : une zone cliquable personnalisée (la disponibilité) et un bouton « Contacter ».
Les clics appellent des callbacks vides : le composant signale l'événement, sans logique applicative.
Tout le code se trouve dans `MainActivity.kt`, organisé en plusieurs composables réutilisables.

## Notions utilisées

- Composables (`@Composable`) et découpage de l'interface
- Paramètres de composables (données et callbacks)
- `Modifier` et chaînage de modifiers (`fillMaxWidth`, `padding`, `clickable`, `semantics`)
- Padding externe / padding interne
- Callbacks (`onClick: () -> Unit`)
- Previews (`@Preview`) avec deux jeux de données
- Accessibilité de base (`semantics`, `Role.Button`, `contentDescription`)
- `Scaffold` et `innerPadding`, `Surface`, `Column`, `Text`, `Button`

## Explication courte

**1. Quels composables avez-vous créés ?**

Sept composables : `ProfilScreen`, `ProfilCard`, `ProfilHeader`, `SkillItem`, `InfoSecondaire`, `ActionZone` et `ActionButton`.
Deux composables de preview s'y ajoutent : `ProfilScreenJuniorPreview` et `ProfilScreenFullStackPreview`.

**2. Quel composable représente l'écran principal ?**

`ProfilScreen`. Il contient le `Scaffold` et affiche `ProfilCard`. Il est appelé depuis `setContent` dans `MainActivity`.

**3. Où utilisez-vous un padding externe ?**

Le padding externe est appliqué par le parent sur le composant enfant :

- Dans `ProfilScreen` : `Modifier.padding(innerPadding).padding(16.dp)` est passé à `ProfilCard` pour le placer dans l'écran.
- Dans `ProfilCard` : `Modifier.padding(top = 16.dp)` est passé à `InfoSecondaire` et à `ActionZone`, et `Modifier.fillMaxWidth().padding(top = 8.dp)` à `ActionButton`, pour les espacer les uns des autres.

**4. Où utilisez-vous un padding interne ?**

Le padding interne est appliqué à l'intérieur d'un composant pour espacer son contenu :

- Dans `ProfilCard` : `Column(modifier = Modifier.padding(16.dp))` crée la marge entre le bord de la fiche et son contenu.
- Dans `ActionZone` : `Text(modifier = Modifier.padding(16.dp))` agrandit la zone cliquable autour du texte.
- Dans `SkillItem` : `modifier.padding(vertical = 2.dp)` espace les compétences entre elles.

**5. Quel composant reçoit un callback ?**

`ActionZone` et `ActionButton` reçoivent un paramètre `onClick: () -> Unit`.
Ils signalent le clic, et c'est `ProfilCard` qui fournit le callback (actuellement un commentaire `TODO`).

**6. Quelle information d'accessibilité avez-vous ajoutée ?**

`ActionZone` est une zone cliquable personnalisée. Je lui ai associé un bloc `semantics` avec :

- `role = Role.Button` : un lecteur d'écran l'annonce comme un bouton ;
- `contentDescription = descriptionAccessibilite` : une description claire de l'action (« Voir la disponibilité de … »).

`ActionButton` utilise un `Button` Material, déjà plus explicite qu'une zone personnalisée : son rôle de bouton et son texte sont annoncés automatiquement, sans configuration.

**7. Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?**

- Ils reçoivent leurs données en paramètres (nom, rôle, compétence, libellé, texte de bouton, callback) et n'affichent donc pas toujours les mêmes informations.
- Chacun accepte `modifier: Modifier = Modifier` et l'utilise, ce qui laisse le parent décider de son placement externe.
- Aucun texte du profil n'est écrit en dur dans les composants : les deux previews affichent deux profils différents avec les mêmes composables.

Chaîne de modifiers utilisée dans `ActionZone` : `fillMaxWidth()` → `clickable { onClick() }` → `semantics { ... }`.
La largeur s'étend à tout l'écran, la zone devient cliquable, puis son intention est décrite pour l'accessibilité.
Le padding interne est placé dans le `Text` enfant : il fait donc partie de la surface cliquable.

## Captures d'écran

Les captures d'écrans se situent dans le dossier screenshots à la racine du projet
## Lien du dépôt

https://github.com/MaximeBjrd/ProfilDeveloppeurCompose
