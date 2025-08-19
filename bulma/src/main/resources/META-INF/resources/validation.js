const originalHelpTexts = {};

// noinspection JSUnusedGlobalSymbols
function validate(formControl, description) {
    const id = formControl.name;
    const help = document.querySelector(`.field:has([name=${id}]) .help`);
    // TODO add help, if there is none, yet (this is not trivial)
    if (!help) return;

    if (formControl.validity?.valid) {
        help.textContent = originalHelpTexts[id];
        help.className = 'help';
        return true;
    }
    if (!originalHelpTexts[id]) originalHelpTexts[id] = help.textContent;
    let error;
    if (formControl.validity.valueMissing) {
        error = `The ${description} is required.`;
    } else if (formControl.validity.typeMismatch) {
        error = `Entered value needs to be a valid ${description}.`;
    } else if (formControl.validity.tooShort) {
        error = `The ${description} should be at least ${formControl.minLength} characters; you entered ${formControl.value.length}.`;
    } else {
        error = `Invalid ${description}.`;
    }
    // TODO support other validations:
    // badInput
    // customError
    // patternMismatch
    // rangeOverflow
    // rangeUnderflow
    // stepMismatch
    // tooLong
    help.textContent = error;
    help.className = 'help has-text-danger';
    return false;
}
