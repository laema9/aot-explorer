package com.example.aot_api.model

sealed class CharacterListUiState {
    data object Loading : CharacterListUiState()

    data class Success(val characters: List<Character>) : CharacterListUiState()

    data class Error(val message: String) : CharacterListUiState()
}

sealed class CharacterDetailUiState {
    data object Loading : CharacterDetailUiState()
    data class Success(val character: Character) : CharacterDetailUiState()
    data class Error(val message: String) : CharacterDetailUiState()
}
