package com.example.aot_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aot_api.model.Character
import com.example.aot_api.model.CharacterDetailUiState
import com.example.aot_api.model.CharacterListUiState
import com.example.aot_api.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class CharacterViewModel : ViewModel() {
    private val _listUiState = MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)
    private val _detailUiState = MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val listUiState: StateFlow<CharacterListUiState> = _listUiState.asStateFlow()
    val detailUiState: StateFlow<CharacterDetailUiState> = _detailUiState.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    private var allCharacters: List<Character> = emptyList()

    init {
        loadCharacters()
    }
    fun loadCharacters() {
        viewModelScope.launch {
            _listUiState.value = CharacterListUiState.Loading
            try {
                val response = RetrofitClient.apiService.getCharacters(page = 1)
                allCharacters = response.results
                _listUiState.value = CharacterListUiState.Success(allCharacters)

            } catch (e: IOException) {
                _listUiState.value = CharacterListUiState.Error(
                    "Erreur réseau"
                )
            } catch (e: Exception) {
                _listUiState.value = CharacterListUiState.Error(
                    "Erreur inattendue : ${e.message}"
                )
            }
        }
    }

    fun loadCharacterDetail(id: Int) {
        viewModelScope.launch {
            _detailUiState.value = CharacterDetailUiState.Loading
            try {
                val character = RetrofitClient.apiService.getCharacterById(id)
                _detailUiState.value = CharacterDetailUiState.Success(character)

            } catch (e: IOException) {
                _detailUiState.value = CharacterDetailUiState.Error(
                    "Erreur réseau"
                )
            } catch (e: Exception) {
                _detailUiState.value = CharacterDetailUiState.Error(
                    "Impossible de charger ce personnage : ${e.message}"
                )
            }
        }
    }
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        val filtered = if (query.isBlank()) {
            allCharacters
        } else {
            allCharacters.filter { character ->
                character.name.contains(query, ignoreCase = true)
            }
        }
        _listUiState.value = CharacterListUiState.Success(filtered)
    }
}
