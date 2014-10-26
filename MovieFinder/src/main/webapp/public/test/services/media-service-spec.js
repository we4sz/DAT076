describe('Service: movieFinder.services.MediaService', function () {
    'use strict';

    var httpBackend, MediaService;

    beforeEach(function () {
        // Set up the module that hold our service
        module('movieFinder.services');

        // Get an instance of the service itself
        // and instance of the http mock service.
        inject(function ($httpBackend, _MediaService_) {
            httpBackend = $httpBackend;
            MediaService = _MediaService_;
        });
    });

    afterEach(function () {
        // Make sure no http requests are missing after each test
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('#getList', function(){
        it('should resolve the promise with the data on a successful request', function() {
            var mediaService = new MediaService('test/api/');
            var content = [{test: 'test'}];
            var wasResolved = false;
            
            httpBackend.expectGET('test/api/').respond({content: content});
            
            mediaService.getList().then(function(res) {
                wasResolved = true;
                expect(res).toEqual(content);
            });
            httpBackend.flush();
            
            expect(wasResolved).toBe(true);
        });

        it('should reject the promise on a failed request', function() {
            var mediaService = new MediaService('test/api/');
            var wasRejected = false;
            
            httpBackend.expectGET('test/api/').respond(500);
            
            mediaService.getList().then(null, function() {
                wasRejected = true;
            });
            httpBackend.flush();
            
            expect(wasRejected).toBe(true);
        });
    });

    describe('#getListByFilter', function() {
        it('should resolve the promise with the data on a successful request', function() {
            var mediaService = new MediaService('test/api/');
            var content = [{test: 'test'}];
            var wasResolved = false;
            
            httpBackend.expectGET('test/api/?test=test').respond({content: content});
            
            mediaService.getListByFilter({test: 'test'}).then(function(res) {
                wasResolved = true;
                expect(res).toEqual(content);
            });
            httpBackend.flush();
            
            expect(wasResolved).toBe(true);
        });

        it('should reject the promise on a failed request', function() {
            var mediaService = new MediaService('test/api/');
            var wasRejected = false;
            
            httpBackend.expectGET('test/api/').respond(500);
            
            mediaService.getListByFilter().then(null, function() {
                wasRejected = true;
            });
            httpBackend.flush();
            
            expect(wasRejected).toBe(true);
        });

        it('should handle multiple filter properties', function() {
            var mediaService = new MediaService('test/api/');
            var content = [{test: 'test'}];
            
            httpBackend.expectGET('test/api/?test=test&test2=test2').respond({content: content});
            
            mediaService.getListByFilter({test: 'test', test2: 'test2'});
            httpBackend.flush();
        });
    });

    describe('#getById', function() {
        it('should resolve the promise with the data on a successful request', function() {
            var mediaService = new MediaService('test/api/');
            var content = {test: 'test'};
            var wasResolved = false;
            
            httpBackend.expectGET('test/api/1').respond(content);
            
            mediaService.getById(1).then(function(res) {
                wasResolved = true;
                expect(res).toEqual(content);
            });
            httpBackend.flush();
            
            expect(wasResolved).toBe(true);
        });

        it('should reject the promise on a failed request', function() {
            var mediaService = new MediaService('test/api/');
            var wasRejected = false;
            
            httpBackend.expectGET('test/api/1').respond(500);
            
            mediaService.getById(1).then(null, function() {
                wasRejected = true;
            });
            httpBackend.flush();
            
            expect(wasRejected).toBe(true);
        });
    });

});
