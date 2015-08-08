angular.module("openkp.kontekstAplikacji", []).factory("kontekstAplikacji", function() {
	// dane kontekstowe dla całej aplikacji
	var kontekstAplikacji = {
		// zalogowany użytkownik
		uzytkownik : {
			nazwa : 'Andrzej',
			zatrudniony : 'Zatrudniony od lutego 2015',
			status : ' aktywny'
		}
	};
	return {
		getUzytkownik : function() {
			return kontekstAplikacji.uzytkownik;
		}		
	}
});