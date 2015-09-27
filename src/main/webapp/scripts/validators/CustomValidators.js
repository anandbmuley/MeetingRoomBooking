var ALPHABETS_REGEXP = /^[a-zA-Z]*$/;
app.directive('alphabet', function() {
	return {
		require : 'ngModel',
		link : function(scope, ele, attrs, ctrl) {
			ctrl.$validators.alphabet = function(modelValue, viewValue) {
				if (ctrl.$isEmpty(modelValue)) {
					return true;
				}
				if (ALPHABETS_REGEXP.test(viewValue)) {
					// it is valid
					return true;
				}
				// it is invalid
				return false;
			}
		}
	}
});