var Book = new Class({
	initialize: function(title, author, yearPublished, branchLocation, callNumber, barcodeNumber,
	                     status) {
       this.title = title;
       this.author = author; 
       this.yearPublished = yearPublished;
       this.branchLocation = branchLocation;
       this.callNumber = callNumber;
       this.barcodeNumber = barcodeNumber;
       this.status = status;
       this.isOverdue = false;
       this.overdueFee = 0.0;
	}
});