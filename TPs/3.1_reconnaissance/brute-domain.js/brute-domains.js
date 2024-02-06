const dns = require('dns');
const promises = [];
const generateSubdomains = function (length) {
    /*
    * A list of characters from which to generate subdomains.
    *
    * This can be altered to include less common characters
    * like '-'.
    *
    * Chinese, Arabic, and Latin characters are also
    * supported by some browsers.
    */
    const charset = 'abcdefghijklmnopqrstuvwxyz'.split('');
    let subdomains = charset;
    let subdomain;
    let letter;
    let temp;
    /*
    * Time Complexity: o(n*m)
    * n = length of string
    * m = number of valid characters
    */
    for (let i = 1; i < length; i++) {
        temp = [];
        for (let k = 0; k < subdomains.length; k++) {
            subdomain = subdomains[k];
            for (let m = 0; m < charset.length; m++) {
                letter = charset[m];
                temp.push(subdomain + letter);
            }
        }
        subdomains = temp
    }
    return subdomains;
}

// const subdomains = generateSubdomains(3);
const subdomains = ['plbsi-prod','plbsi-rec','plbsi-dev','plbsi-qa','plbsi-test','plbsi-preprod','plbsi-preproduction']
console.log(subdomains.length + ' subdomains generated.');

console.log(subdomains);


/*
* Iterate through each subdomain, and perform an asynchronous
* DNS query against each subdomain.
*
* This is much more performant than the more common `dns.lookup()`
* because `dns.lookup()` appears asynchronous from the JavaScript,
* but relies on the operating system's getaddrinfo(3) which is
* implemented synchronously.
*/
subdomains.forEach((subdomain) => {
    promises.push(new Promise((resolve, reject) => {
        dns.lookup(`${subdomain}.plb.fr`, function (err, ip) {
            console.log('DNS lookup for ' + subdomain + 'err: ' + err + ' ip: ' + ip);

            return resolve({ subdomain: subdomain, ip: ip });
        });
    }));
});
console.log('DNS lookup done.');

// after all of the DNS queries have completed, log the results
Promise.all(promises).then(function (results) {
    results.forEach((result) => {
        console.log(result);
        if (!!result.ip) {
            console.log(result);
        }
    });
});

