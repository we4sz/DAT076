/* jshint node: true, strict: false */

// Node modules
var path = require('path');
var gulp = require('gulp');
var karma = require('karma').server;

// Gulp modules
var gutil = require('gulp-util');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var del = require('del');
var minifyCSS = require('gulp-minify-css');
var imagemin = require('gulp-imagemin');
var plumber = require('gulp-plumber');
var templateCache = require('gulp-angular-templatecache');
var jshint = require('gulp-jshint');
var ngAnnotate = require('gulp-ng-annotate');
var sourcemaps = require('gulp-sourcemaps');


// The path of the bower_components folder
var BOWER_PATH = path.join(__dirname, 'bower_components');

// The path of the output folders
var BUILD_BASE_PATH = path.join(__dirname, '../build/');
var BUILD_JS_PATH = path.join(BUILD_BASE_PATH, '/js');
var BUILD_CSS_PATH = path.join(BUILD_BASE_PATH, '/css');
var BUILD_IMG_PATH = path.join(BUILD_BASE_PATH, '/img');
var BUILD_TEMPLATE_PATH = path.join(BUILD_BASE_PATH, '/js');

// Paths to input files/folders
var PATHS = {
    scripts: ['src/**/*.js'],
    external_scripts: [
        path.join(BOWER_PATH, 'jquery/dist/jquery.min.js'),
        path.join(BOWER_PATH, 'bootstrap/dist/js/bootstrap.min.js'),
        path.join(BOWER_PATH, 'angular/angular.min.js'),
        path.join(BOWER_PATH, 'angular-route/angular-route.min.js')
    ],
    styles: [
        'css/**/*.css',
        path.join(BOWER_PATH, 'bootstrap/dist/css/bootstrap.css')
    ],
    images: [
        'img/**/*.png',
        'img/**/*.gif',
        'img/**/*.jpg'
    ],
    templates : [
        'partials/**/*.html'
    ]
};

var onError = function (err) {
    gutil.beep();
    console.log(err.stack);
};

// Gulp task clean
// Removes the directory at BUILD_BASE_PATH
gulp.task('clean', function (cb) {
    del([BUILD_BASE_PATH], {force: true}, cb);
});

// Gulp task extScripts
// Combindes and uglifies all external scripts to a lib.min.js file.
gulp.task('extScripts', ['clean'], function() {
    return gulp.src(PATHS.external_scripts)
        .pipe(plumber({
            errorHandler: onError
        }))
        .pipe(uglify())
        .pipe(concat('lib.min.js'))
        .pipe(gulp.dest(BUILD_JS_PATH));
});

// Gulp task scripts
// Combindes and uglifies all app scripts to an app.min.js file.
gulp.task('scripts', ['clean', 'extScripts'], function () {
    return gulp.src(PATHS.scripts)
        .pipe(plumber({
            errorHandler: onError
        }))
        .pipe(sourcemaps.init())
        .pipe(ngAnnotate())
        .pipe(concat('app.min.js'))
        .pipe(uglify())
        .pipe(sourcemaps.write())
        .pipe(gulp.dest(BUILD_JS_PATH));
        
});

// Gulp task css
// Minifies and combindes all css to an all.min.css file.
gulp.task('css', ['clean'], function () {
    return gulp.src(PATHS.styles)
        .pipe(minifyCSS())
        .pipe(concat('all.min.css'))
        .pipe(gulp.dest(BUILD_CSS_PATH));
});

// Gulp task images
// Runs all images through imagemin, compresssing them to save bandwidth.
gulp.task('images', ['clean'], function() {
    return gulp.src(PATHS.images)
        .pipe(imagemin())
        .pipe(gulp.dest(BUILD_IMG_PATH));
});

// Gulp task templates
// Combindes all angular js partials/templates to a file called templates.js.
gulp.task('templates', ['clean', 'scripts'], function() {
    return gulp.src(PATHS.templates)
        .pipe(templateCache({
            module: 'movieFinder.templates',
            root: 'partials/'
        }))
        .pipe(gulp.dest(BUILD_TEMPLATE_PATH));
});

// Gulp task lint
// Runs all the source javascript files trough jsHint, detecting 
// various errors.
gulp.task('lint', function() {
    return gulp.src(PATHS.scripts)
        .pipe(jshint())
        .pipe(jshint.reporter('jshint-stylish'))
        .pipe(jshint.reporter('fail'));
});

// Gulp test task
// Runs tests on the frontend
gulp.task('test', ['lint'], function(done) {
    karma.start({
        configFile: __dirname + '/karma.conf.js',
        singleRun: true,
        browsers: ['PhantomJS']
    }, done);
});

// Gulp task default
// The default task (called when you run `gulp` from cli).
// Runs all of the above tasks.
gulp.task('default', ['lint', 'scripts', 'css', 'images', 'templates']);
