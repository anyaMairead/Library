window.addEvent("domready", function() {

    function selectPanel(name) {
	    var panels=["initial", "bookSearchForm", "bookSearchResults", "bookDetail", 
	                "patronSearchForm", "patronSearchResults", "patronDetail"];
	    panels.forEach(function(id) {
		    if (id === name) {
			    $(id).show();
		    } else {
			    $(id).hide();
		    }
	    })
    }

    function displayBookResults(results) {
        selectPanel("bookSearchResults");
        $("numBookResults").set("html", 
          "Result of search through book catalogue: " + results.length + " books found");
        $("bookResultList").set("html", 
          results.map(function(b) { return b.title + " by " + b.author;}).join("<br/>"));
    }
    
    function searchForBook() {
    	var criteria = $(bookCriteriaDropdown).getSelected()[0].value;
    	var searchFor = $("bookSearchField").get("value");
    	if (criteria==="barcode") {
            var results = bookList.filter(function(book) { return book.barcodeNumber === searchFor;});
    	} else if (criteria==="title") {
    		var results = bookList.filter(function(book) { return book.title.indexOf(searchFor) >= 0;});
    	}
        displayBookResults(results);
    }
    

    $("bookButton").addEvent("click", function() { selectPanel("bookSearchForm");});
    $("patronButton").addEvent("click", function() { selectPanel("patronSearchForm");});
    $$("input.newSearchButton").addEvent("click", function() { selectPanel("initial");});
    $("searchForBookButton").addEvent("click", searchForBook);
    selectPanel("initial");
});