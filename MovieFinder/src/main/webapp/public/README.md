# Frontend of the MovieFinder web-app
This folder holds the sources for the frontend side of the MovieFinder web-app. 

## Special files and files
`/bower_components/` - Folder generated by bower, holding third party libraries our client side depends on, such as angular and jQuery.
`/node_modules/` - Folder generated by node, holding third party libraries required to build the client side of our app, such as bower and gulp.
`.jshintrc` - File specifying the rules that [JSHint](http://jshint.com/) will enforce on our javascript files.
`bower.json` - File listing third party libraries our frontend depends on.
`gulpfile.js` - File specifying the build process for our frontend.
`karma.conf.js` - File configuring how the javascript test runner Karma should run our frontend tests.
`package.json` - File declaring node dependencies for building the sources. 

## Build process

The sources are built using [gulpjs](http://gulpjs.com/) automatically when the maven project is packaged. *IMPORTANT:* For the process to run [Nodejs, npm](http://nodejs.org) and [Git](http://git-scm.com/) must be installed and added to the PATH.

The full process run by maven looks like;

1. `npm install`
	1. Node dependencies defined in package.json are downloaded and installed.
	2. Bower dependencies defined in bower.json are downloaded.
1. `npm run-script test` 
	1. Javascript files are linted using JSHint.
	2. Javascript unit tests in the /test folder is run using PhantomJS, a headless browser.
2. `npm run-script build`
	1. External javascript files are minified and combinded to a single file.
	2. App javascript files are annotated for angular, minified, combined to a single file and source mapped.
	3. External css files are minified and combined to a single file.
	4. App css files are minified and combined to a single file.
	4. Images are compressed.
	5. Angular partials are combined to a single file.

The different npm commands can also be run manually from a command line. The resulting files will be written to `../build/`.
