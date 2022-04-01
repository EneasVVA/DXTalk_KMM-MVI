import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        KoinModule().initiOS()
        PlatformModuleKt.debugBuild()
    }
    
	var body: some Scene {
		WindowGroup {
            NavigationView {
                LoginView().navigationTitle("Login")
            }
		}
	}
}
