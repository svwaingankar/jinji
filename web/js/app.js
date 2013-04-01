
function consoleModel() {
    var self=this;

    self.apiList = ko.observableArray([
            { name: 'Dist', url:'/api/estimate/offerDistribution',formId: 'offerDistribution' }

        ]);


}

ko.applyBindings(new consoleModel());