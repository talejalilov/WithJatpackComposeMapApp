package com.talejalilov.yukatechexercise.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.libraries.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FeedScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "FeedScreen")
        }

        val mapView = rememberMapViewWithLifecycle()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White)
        ) {
            AndroidView({ mapView}) {mapViews->
                CoroutineScope(Dispatchers.Main).launch {
                    val googleMap =mapView.getMapAsync{
                        map->

                        map.uiSettings.isZoomControlsEnabled = true

                        val pickUp =  LatLng(-35.016, 143.321)
                        val destination = LatLng(-32.491, 147.309)
                     //   map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination,6f))
                        val markerOptions = MarkerOptions()
                            .title("Sydney Opera House")
                            .position(pickUp)
                     //   map.addMarker(markerOptions)

                        val markerOptionsDestination = MarkerOptions()
                            .title("Restaurant Hubert")
                            .position(destination)
                     //   map.addMarker(markerOptionsDestination)

//                        map.addPolyline(
//                            PolylineOptions().add( pickUp,
//                                LatLng(-34.747, 145.592),
//                                LatLng(-34.364, 147.891),
//                                LatLng(-33.501, 150.217),
//                                LatLng(-32.306, 149.248),
//                                destination))

                    }

                }



            }
        }




        BottomNavigationMenu(selectedItem = BottomNavigationItem.FEED, navController =navController)
    }
}